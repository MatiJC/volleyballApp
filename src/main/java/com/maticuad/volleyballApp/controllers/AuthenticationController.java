package com.maticuad.volleyballApp.controllers;

import com.maticuad.volleyballApp.auth.AuthenticationResponse;
import com.maticuad.volleyballApp.dto.AuthDTO;
import com.maticuad.volleyballApp.dto.RegistrationDTO;
import com.maticuad.volleyballApp.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegistrationDTO request) throws Exception {
        return ResponseEntity.ok(authenticationService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthDTO request) throws Exception {
        return ResponseEntity.ok(authenticationService.loginUser(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
    }

}
