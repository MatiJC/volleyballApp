package com.maticuad.volleyballApp.dto;

import com.maticuad.volleyballApp.models.Role;

public record UserDTO(String username, String firstName,
                      String lastName, Role role) {
}
