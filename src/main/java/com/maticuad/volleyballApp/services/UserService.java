package com.maticuad.volleyballApp.services;

import com.maticuad.volleyballApp.dto.UserDTO;
import com.maticuad.volleyballApp.models.User;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();
    UserDTO findByUsername(String username);
    void saveUser(User user);
}
