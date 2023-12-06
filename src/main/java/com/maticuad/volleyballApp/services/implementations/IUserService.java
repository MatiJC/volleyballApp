package com.maticuad.volleyballApp.services.implementations;

import com.maticuad.volleyballApp.dto.UserDTO;
import com.maticuad.volleyballApp.models.User;
import com.maticuad.volleyballApp.repositories.UserRepository;
import com.maticuad.volleyballApp.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class IUserService implements UserService, UserDetailsService {

    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    public IUserService(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Inside user service");
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User is not valid"));
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map
                (user -> new UserDTO(user.getUsername(), user.getFirstName(), user.getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow
                (() -> new UsernameNotFoundException("User is not valid"));
        return new UserDTO(user.getUsername(), user.getFirstName(), user.getLastName());
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

}
