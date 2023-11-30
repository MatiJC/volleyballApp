package com.maticuad.volleyballApp.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @SequenceGenerator(
            name = "team_sequence",
            sequenceName = "team_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_sequence")
    @Column(name = "team_id")
    private Long id;
    private String teamName;
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private Set<Player> players;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Team() {
    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team(String teamName, Set<Player> players) {
        this.teamName = teamName;
        this.players = players;
    }

    public Long getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
