package com.maticuad.volleyballApp.dto;

import com.maticuad.volleyballApp.models.Gender;
import com.maticuad.volleyballApp.models.Player;

import java.util.Set;

public record TeamDTO(String teamName, Gender gender,
                      Set<Player> players) {
}
