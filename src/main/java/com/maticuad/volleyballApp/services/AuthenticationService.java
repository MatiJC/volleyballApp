package com.maticuad.volleyballApp.services;

import com.maticuad.volleyballApp.auth.AuthenticationResponse;
import com.maticuad.volleyballApp.dto.AuthDTO;
import com.maticuad.volleyballApp.dto.RegistrationDTO;
import com.maticuad.volleyballApp.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {
     AuthenticationResponse registerUser(RegistrationDTO request);
     AuthenticationResponse loginUser(AuthDTO request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
    void saveUserToken(User user, String jwtToken);
    void revokeAllUserTokens(User user);
}
