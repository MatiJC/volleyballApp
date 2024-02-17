package com.maticuad.volleyballApp.services.implementations;

import com.maticuad.volleyballApp.dto.PlayerDTO;
import com.maticuad.volleyballApp.exceptions.ApiException;
import com.maticuad.volleyballApp.models.Player;
import com.maticuad.volleyballApp.repositories.PlayerRepository;
import com.maticuad.volleyballApp.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IPlayerService implements PlayerService {
    private final PlayerRepository playerRepository;

    @Override
    public List<PlayerDTO> findAllPlayers() {
        return playerRepository.findAll().stream().map
                        (player -> new PlayerDTO(player.getFirstName(), player.getLastName(), player.getShirtNumber(),
                                player.getPosition(), player.getTeam(), player.getGender()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlayerDTO> findPlayersByLastName(String lastName) {
        return playerRepository.findByLastName(lastName).stream().map
                        (player -> new PlayerDTO(player.getFirstName(), player.getLastName(), player.getShirtNumber(),
                                player.getPosition(), player.getTeam(), player.getGender()))
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDTO findPlayerById(Long id) throws ApiException {
        return playerRepository.findById(id).map
                        (player -> new PlayerDTO(player.getFirstName(), player.getLastName(), player.getShirtNumber(),
                                player.getPosition(), player.getTeam(), player.getGender())).orElseThrow(() ->
                new ApiException("Player is not valid"));
    }

    @Override
    public void savePlayer(Player player) {

    }

    @Override
    public void createPlayer(PlayerDTO player) {

    }

    @Override
    public void updatePlayer(PlayerDTO player) {

    }

    public String playerFullName(PlayerDTO player) {
        return player.firstName() + " " + player.lastName();
    }
}
