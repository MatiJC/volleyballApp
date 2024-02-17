package com.maticuad.volleyballApp.services;

import com.maticuad.volleyballApp.dto.PlayerDTO;
import com.maticuad.volleyballApp.dto.TeamDTO;
import com.maticuad.volleyballApp.exceptions.ApiException;
import com.maticuad.volleyballApp.models.Team;

import java.util.List;

public interface TeamService {
    List<TeamDTO> findAllTeams();
    TeamDTO findTeamByName(String teamName) throws ApiException;
    TeamDTO findTeamById(Long id) throws ApiException;
    List<PlayerDTO> getTeamRoster(TeamDTO team) throws ApiException;
    void saveTeam(Team team);
    void createTeam();
    void updateTeam(Long teamId);
    void transferPlayer(Long playerId, String teamFromName, String teamDestinationName) throws ApiException;
    void removePlayer(PlayerDTO player, TeamDTO team) throws ApiException;
    void addPlayer(PlayerDTO player, TeamDTO team) throws ApiException;
    boolean isPlayerInTeam(PlayerDTO player, TeamDTO team);
    boolean isRosterEmpty(TeamDTO team);
    boolean checkRosterCapacity(TeamDTO team);
    boolean isGenderValid(PlayerDTO player, TeamDTO team);
}
