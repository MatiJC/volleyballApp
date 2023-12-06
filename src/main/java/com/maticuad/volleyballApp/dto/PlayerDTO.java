package com.maticuad.volleyballApp.dto;

import com.maticuad.volleyballApp.models.Gender;
import com.maticuad.volleyballApp.models.Position;
import com.maticuad.volleyballApp.models.Team;

public record PlayerDTO(String firstName, String lastName,
                        Integer shirtNumber, Position position,
                        Team team, Gender gender) {
}
