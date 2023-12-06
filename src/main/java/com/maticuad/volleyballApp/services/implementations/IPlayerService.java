package com.maticuad.volleyballApp.services.implementations;

import com.maticuad.volleyballApp.dto.PlayerDTO;
import com.maticuad.volleyballApp.models.Player;
import com.maticuad.volleyballApp.repositories.PlayerRepository;
import com.maticuad.volleyballApp.services.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPlayerService implements PlayerService {
    private final PlayerRepository playerRepository;

    public IPlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<PlayerDTO> findAllPlayers() {
        return null;
    }

    @Override
    public List<PlayerDTO> findPlayersByLastName(String lastName) {
        return null;
    }

    @Override
    public PlayerDTO findPlayerById(Long id) {
        return null;
    }

    @Override
    public void savePlayer(Player player) {

    }
}
