package com.maticuad.volleyballApp.services.implementations;

import com.maticuad.volleyballApp.dto.PlayerDTO;
import com.maticuad.volleyballApp.dto.TeamDTO;
import com.maticuad.volleyballApp.models.Team;
import com.maticuad.volleyballApp.repositories.TeamRepository;
import com.maticuad.volleyballApp.services.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ITeamService implements TeamService {
    private final TeamRepository teamRepository;

    public ITeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<TeamDTO> findAllTeams() {
        return teamRepository.findAll().stream().map
                        (team -> new TeamDTO(team.getTeamName(), team.getGender(), team.getPlayers()))
                .collect(Collectors.toList());
    }

    @Override
    public TeamDTO findTeamByName(String teamName) {
        Team team = teamRepository.findByTeamName(teamName).orElseThrow();
        return new TeamDTO(team.getTeamName(), team.getGender(), team.getPlayers());
    }

    @Override
    public TeamDTO findTeamById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow();
        return new TeamDTO(team.getTeamName(), team.getGender(), team.getPlayers());
    }

    @Override
    public List<PlayerDTO> getTeamRoster(String teamName) {
        return null;
    }

    @Override
    public void saveTeam(Team team) {
        teamRepository.save(team);

    }

    @Override
    public void transferPlayer(String playerName, String teamFromName, String teamDestinationName) {

    }

    @Override
    public void removePlayer(String playerName, String teamName) {

    }

    @Override
    public void addPlayer(String playerName, String teamName) {

    }

}
