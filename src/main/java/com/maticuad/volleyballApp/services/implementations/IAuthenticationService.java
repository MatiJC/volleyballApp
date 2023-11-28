package com.maticuad.volleyballApp.services.implementations;

import com.maticuad.volleyballApp.auth.AuthenticationResponse;
import com.maticuad.volleyballApp.dto.AuthDTO;
import com.maticuad.volleyballApp.dto.RegistrationDTO;
import com.maticuad.volleyballApp.models.Role;
import com.maticuad.volleyballApp.models.User;
import com.maticuad.volleyballApp.repositories.UserRepository;
import com.maticuad.volleyballApp.services.AuthenticationService;
import com.maticuad.volleyballApp.services.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IAuthenticationService implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public IAuthenticationService(UserRepository userRepository, PasswordEncoder encoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponse registerUser(RegistrationDTO request) {
        User user = new User(request.username(),
                encoder.encode(request.password()),
                request.firstName(),
                request.lastName(),
                Role.USER);
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    @Override
    public AuthenticationResponse authenticateUser(AuthDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(),
                        request.password())
        );
        User user = userRepository.findByUsername(request.username()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
