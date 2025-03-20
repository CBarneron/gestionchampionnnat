package com.ipi.gestionchampionnat.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    private long id;

    @NotNull(message = "Le champ nom de journée ne peut pas être null")
    @NotBlank(message = "Le champ nom de journée ne peut pas être vide")
    private String number;

    @NotNull(message = "L'id de championnat ne peut pas être null")
    @Min(value = 1, message = "L'id de championnat ne peut être négatif")
    private Long championshipId;


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
}
