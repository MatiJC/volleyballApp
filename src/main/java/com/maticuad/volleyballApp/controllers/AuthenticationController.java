package com.maticuad.volleyballApp.controllers;

import com.maticuad.volleyballApp.auth.AuthenticationResponse;
import com.maticuad.volleyballApp.dto.AuthDTO;
import com.maticuad.volleyballApp.dto.RegistrationDTO;
import com.maticuad.volleyballApp.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthDTO request) throws Exception {
        return ResponseEntity.ok(authenticationService.authenticateUser(request));
    }

}
