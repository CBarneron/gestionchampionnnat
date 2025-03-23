package com.ipi.gestionchampionnat.models;

import java.util.Optional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer team1Point;

    private Integer team2Point;

    @ManyToOne
    @JoinColumn(name = "idTeam1", nullable = false)
    @NotNull
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "idTeam2", nullable = false)
    @NotNull
    private Team team2;

    @ManyToOne
    @JoinColumn(name = "idDay", nullable = false)
    @NotNull
    private Day day;

    public Game() {}

    /**
     * Objet Game
     * @param team1Point points de l'équipe 1
     * @param team2Point points de l'équipe 2
     * @param team1 objet équipe 1
     * @param team2 objet équipe 2
     * @param day journée associée
     */
    public Game(Integer team1Point, Integer team2Point, Team team1, Team team2, Day day) {
        this.team1Point = team1Point;
        this.team2Point = team2Point;
        this.team1 = team1;
        this.team2 = team2;
        this.day = day;
    }

}