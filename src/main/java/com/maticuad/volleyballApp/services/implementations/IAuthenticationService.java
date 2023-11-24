package com.maticuad.volleyballApp.services.implementations;

import com.maticuad.volleyballApp.models.Role;
import com.maticuad.volleyballApp.models.User;
import com.maticuad.volleyballApp.repositories.RoleRepository;
import com.maticuad.volleyballApp.repositories.UserRepository;
import com.maticuad.volleyballApp.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class IAuthenticationService implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public User registerUser(String username, String password,
                             String firstName, String lastName) throws Exception {
        Role userRole = roleRepository.findByAuthority("USER").orElseThrow(Exception::new);
        String encodedPassword = encoder.encode(password);

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        return userRepository.save(new User(username, encodedPassword, firstName, lastName, authorities));
    }
}
