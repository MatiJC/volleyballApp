package com.maticuad.volleyballApp.services;

import com.maticuad.volleyballApp.dto.PlayerDTO;
import com.maticuad.volleyballApp.dto.TeamDTO;
import com.maticuad.volleyballApp.models.Team;

import java.util.List;

public interface TeamService {
    List<TeamDTO> findAllTeams();
    TeamDTO findTeamByName(String teamName);
    TeamDTO findTeamById(Long id);
    List<PlayerDTO> getTeamRoster(String teamName);
    void saveTeam(Team team);
    void transferPlayer(String playerName, String teamFromName, String teamDestinationName);
    void removePlayer(String playerName, String teamName);
    void addPlayer(String playerName, String teamName);
}
