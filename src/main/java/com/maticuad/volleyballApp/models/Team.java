package com.maticuad.volleyballApp.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
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
    @Setter(AccessLevel.NONE)
    private Long id;
    private String teamName;
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private Set<Player> players = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team(String teamName, Set<Player> players, Gender gender) {
        this.teamName = teamName;
        this.players = players;
        this.gender = gender;
    }
}
