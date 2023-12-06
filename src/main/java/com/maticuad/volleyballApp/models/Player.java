package com.maticuad.volleyballApp.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "players")
public class Player {
    @Id
    @SequenceGenerator(
            name = "player_sequence",
            sequenceName = "player_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_sequence")
    @Column(name = "player_id")
    @Setter(AccessLevel.NONE)
    private Long id;
    private String firstName;
    private String lastName;
    private Integer shirtNumber;
    @Enumerated(EnumType.STRING)
    private Position position;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    /* private Float height;
    private Float weight;
    private LocalDate birthdate;
     */

    public Player(String firstName, String lastName, Integer shirtNumber, Position position, Team team, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.shirtNumber = shirtNumber;
        this.position = position;
        this.team = team;
        this.gender = gender;
    }
}
