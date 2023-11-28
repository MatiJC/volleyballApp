package com.maticuad.volleyballApp.services;

import com.maticuad.volleyballApp.auth.AuthenticationResponse;
import com.maticuad.volleyballApp.dto.AuthDTO;
import com.maticuad.volleyballApp.dto.RegistrationDTO;

public interface AuthenticationService {
     AuthenticationResponse registerUser(RegistrationDTO request);
     AuthenticationResponse authenticateUser(AuthDTO request);
}
