package com.maticuad.volleyballApp.services;

import com.maticuad.volleyballApp.models.User;

public interface AuthenticationService {
    User registerUser(String username, String password,
                      String firstName, String lastName) throws Exception;
}
