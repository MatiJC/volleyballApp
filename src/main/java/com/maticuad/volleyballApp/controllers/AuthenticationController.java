package com.maticuad.volleyballApp.controllers;

import com.maticuad.volleyballApp.dto.RegistrationDTO;
import com.maticuad.volleyballApp.models.User;
import com.maticuad.volleyballApp.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationDTO body) throws Exception {
        return authenticationService.registerUser(body.username(), body.password(),
                body.firstName(), body.lastName());

    }

}
