package com.ipi.gestionchampionnat.models;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le champ name ne peut être null")
    @NotBlank( message = "Le champ name ne peut être vide")
    private String name;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate = LocalDate.now();


    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {
    CascadeType.PERSIST,
    CascadeType.MERGE,
    CascadeType.REMOVE
    },
    mappedBy = "teams")
    @JsonIgnore
    private Set<Championship> championships = new HashSet<>();
   
    
    @OneToMany(mappedBy = "team1", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Game> gamesAsTeam1 = new HashSet<>();

    @OneToMany(mappedBy = "team2", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Game> gamesAsTeam2 = new HashSet<>();

    /**constructeur par défaut */
    public Team() {
    }
   
    /**Constructeur avec paramètres
     *
     * @param name
     * @param creationDate
     */
    public Team(String name, LocalDate creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }
}
