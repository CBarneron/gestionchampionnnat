package com.ipi.gestionchampionnat.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "championships")
public class Championship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Le champ nom du championnat ne peut pas être null")
    @NotBlank(message = "Le champ nom du championnat ne peut pas être vide")
    private String name;

    @NotNull(message = "Le champ date de création du championnat ne peut pas être null")
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "Le champ date de fin du championnat ne peut pas être null")
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull(message = "Le champ points de victoire du championnat ne peut pas être null")
    @Min(value = 0, message = "Le champ points de victoire du championnat ne peut pas être négatif")
    private int wonPoint;

    @NotNull(message = "Le champ points de défaite du championnat ne peut pas être null")
    private int lostPoint;

    @NotNull(message = "Le champ points pour un match null du championnat ne peut pas être null")
    private int drawPoint;

    @ManyToMany
    @JoinTable(name = "TeamChampionship",
        joinColumns = { @JoinColumn(name = "championship_id") },
        inverseJoinColumns = { @JoinColumn(name = "team_id") })
    protected final Set<Team> teams = new HashSet<>();

    /**
     * Constructeur par défaut
     */
    public Championship() {}

    /**
     * Constructeur avec paramètres
     *
     * @param name
     * @param startDate
     * @param endDate
     * @param wonPoint
     * @param lostPoint
     * @param drawPoint
     */
    public Championship(String name, LocalDate startDate, LocalDate endDate, int wonPoint, int lostPoint, int drawPoint) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.wonPoint = wonPoint;
        this.lostPoint = lostPoint;
        this.drawPoint = drawPoint;
    }

    
}
