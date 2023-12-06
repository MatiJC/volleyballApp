package com.maticuad.volleyballApp.services;

import com.maticuad.volleyballApp.dto.PlayerDTO;
import com.maticuad.volleyballApp.models.Player;

import java.util.List;

public interface PlayerService {
    List<PlayerDTO> findAllPlayers();
    List<PlayerDTO> findPlayersByLastName(String lastName);
    PlayerDTO findPlayerById(Long id);
    void savePlayer(Player player);


}
