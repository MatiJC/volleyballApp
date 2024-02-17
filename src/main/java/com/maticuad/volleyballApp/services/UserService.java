package com.maticuad.volleyballApp.services;

import com.maticuad.volleyballApp.dto.ChangePasswordDTO;
import com.maticuad.volleyballApp.dto.UserDTO;
import com.maticuad.volleyballApp.models.User;

import java.security.Principal;
import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();
    UserDTO findByUsername(String username);
    void saveUser(User user);
    void changePassword(ChangePasswordDTO request, Principal user);
}
