package com.maticuad.volleyballApp.dto;

import com.maticuad.volleyballApp.models.Role;

import java.util.Set;

public record UserDTO(String username, String firstName,
                      String lastName, Set<Role> roles) {
}
