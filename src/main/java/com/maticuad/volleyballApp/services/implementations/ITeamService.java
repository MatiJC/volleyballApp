package com.maticuad.volleyballApp.services.implementations;

import com.maticuad.volleyballApp.dto.PlayerDTO;
import com.maticuad.volleyballApp.dto.TeamDTO;
import com.maticuad.volleyballApp.exceptions.ApiException;
import com.maticuad.volleyballApp.models.Team;
import com.maticuad.volleyballApp.repositories.PlayerRepository;
import com.maticuad.volleyballApp.repositories.TeamRepository;
import com.maticuad.volleyballApp.services.PlayerService;
import com.maticuad.volleyballApp.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ITeamService implements TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final PlayerService playerService;

    @Override
    public List<TeamDTO> findAllTeams() {
        return teamRepository.findAll().stream().map
                        (team -> new TeamDTO(team.getTeamName(), team.getGender(), team.getPlayers()))
                .collect(Collectors.toList());
    }

    @Override
    public TeamDTO findTeamByName(String teamName) throws ApiException {
        Team team = teamRepository.findByTeamName(teamName).orElseThrow(() ->
                new ApiException("Team " + teamName + " is not valid"));
        return new TeamDTO(team.getTeamName(), team.getGender(), team.getPlayers());
    }

    @Override
    public TeamDTO findTeamById(Long id) throws ApiException {
        Team team = teamRepository.findById(id).orElseThrow(() ->
                new ApiException("Team is not valid"));
        return new TeamDTO(team.getTeamName(), team.getGender(), team.getPlayers());
    }

    @Override
    public List<PlayerDTO> getTeamRoster(TeamDTO team) {
        return team.players().stream().map
                        (player -> new PlayerDTO(player.getFirstName(), player.getLastName(), player.getShirtNumber(),
                                player.getPosition(), player.getTeam(), player.getGender()))
                .collect(Collectors.toList());
    }

    @Override
    public void saveTeam(Team team) {

    }

    @Override
    public void createTeam(TeamDTO team) {

    }

    @Override
    public void updateTeam(TeamDTO team) {

    }

    @Override
    @Transactional
    public void transferPlayer(Long playerId, String teamFromName, String teamDestinationName) throws ApiException {
        PlayerDTO player = playerService.findPlayerById(playerId);
        TeamDTO fromTeam = this.findTeamByName(teamFromName);
        TeamDTO destinationTeam = this.findTeamByName(teamDestinationName);
        removePlayer(player, fromTeam);
        addPlayer(player, destinationTeam);

    }

    @Override
    public void removePlayer(PlayerDTO player, TeamDTO team) throws ApiException {
        if (isRosterEmpty(team)) {
            throw new ApiException(team.teamName() + " is empty");
        }
        if (!isPlayerInTeam(player, team)) {
            throw new ApiException("Player " + playerService.playerFullName(player) +
                    " is not in team " + team.teamName());
        }
        this.getTeamRoster(team).remove(player);
    }

    @Override
    public void addPlayer(PlayerDTO player, TeamDTO team) throws ApiException {
        if (isPlayerInTeam(player, team)) {
            throw new ApiException("Player " + playerService.playerFullName(player) +
                    " is already in team " + team.teamName());
        }
        if (checkRosterCapacity(team)) {
            throw new ApiException(team.teamName() + " is full");
        }
        this.getTeamRoster(team).add(player);
    }

    @Override
    public boolean isPlayerInTeam(PlayerDTO player, TeamDTO team) {
        return this.getTeamRoster(team).contains(player);
    }

    @Override
    public boolean isRosterEmpty(TeamDTO team) {
        return team.players().isEmpty();
    }

    @Override
    public boolean checkRosterCapacity(TeamDTO team) {
        return team.players().size() >= 14;
    }

    @Override
    public boolean isGenderValid(PlayerDTO player, TeamDTO team) {
        return team.gender().equals(player.gender());
    }

}
