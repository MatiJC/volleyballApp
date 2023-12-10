package com.maticuad.volleyballApp.services.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maticuad.volleyballApp.auth.AuthenticationResponse;
import com.maticuad.volleyballApp.dto.AuthDTO;
import com.maticuad.volleyballApp.dto.RegistrationDTO;
import com.maticuad.volleyballApp.models.Role;
import com.maticuad.volleyballApp.models.Token;
import com.maticuad.volleyballApp.models.TokenType;
import com.maticuad.volleyballApp.models.User;
import com.maticuad.volleyballApp.repositories.TokenRepository;
import com.maticuad.volleyballApp.repositories.UserRepository;
import com.maticuad.volleyballApp.services.AuthenticationService;
import com.maticuad.volleyballApp.services.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IAuthenticationService implements AuthenticationService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    private final PasswordEncoder encoder;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse registerUser(RegistrationDTO request) {
        User user = new User(request.username(),
                encoder.encode(request.password()),
                request.firstName(),
                request.lastName(),
                Role.USER);
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(user, jwtToken);
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    @Override
    public AuthenticationResponse loginUser(AuthDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(),
                        request.password())
        );
        User user = userRepository.findByUsername(request.username()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            User user = userRepository.findByUsername(username)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                AuthenticationResponse authResponse = new AuthenticationResponse(accessToken, refreshToken);
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }


    @Override
    public void saveUserToken(User user, String jwtToken) {
        Token token = new Token(jwtToken, TokenType.BEARER, false, false, user);
        tokenRepository.save(token);
    }

    @Override
    public void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
