package com.maticuad.volleyballApp.services.implementations;

import com.maticuad.volleyballApp.dto.ChangePasswordDTO;
import com.maticuad.volleyballApp.dto.UserDTO;
import com.maticuad.volleyballApp.models.User;
import com.maticuad.volleyballApp.repositories.UserRepository;
import com.maticuad.volleyballApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class IUserService implements UserService, UserDetailsService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

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

    @Override
    public void changePassword(ChangePasswordDTO request, Principal connectedUser) {
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        if (!encoder.matches(request.currentPass(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        if (request.currentPass().equals(request.newPass())) {
            throw new IllegalStateException("The new password is the same as the old one");
        }
        if (!request.newPass().equals(request.confirmationPass())) {
            throw new IllegalStateException("Passwords do not match");
        }

        user.setPassword(encoder.encode(request.newPass()));
        userRepository.save(user);
    }

}
