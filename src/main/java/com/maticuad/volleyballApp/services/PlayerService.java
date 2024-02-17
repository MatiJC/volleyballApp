package com.maticuad.volleyballApp.services;

import com.maticuad.volleyballApp.dto.PlayerDTO;
import com.maticuad.volleyballApp.exceptions.ApiException;
import com.maticuad.volleyballApp.models.Player;

import java.util.List;

public interface PlayerService {
    List<PlayerDTO> findAllPlayers();
    List<PlayerDTO> findPlayersByLastName(String lastName);
    PlayerDTO findPlayerById(Long id) throws ApiException;
    void savePlayer(Player player);
    void createPlayer(PlayerDTO player);
    void updatePlayer(Long playerId);
    String playerFullName(PlayerDTO player);


}
