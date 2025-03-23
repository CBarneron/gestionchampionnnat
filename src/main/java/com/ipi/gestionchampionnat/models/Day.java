package com.ipi.gestionchampionnat.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "days")
public class Day {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Le champ nom de journée ne peut pas être null")
    @NotBlank(message = "Le champ nom de journée ne peut pas être vide")
    private String number;

    @NotNull(message = "L'id de championnat ne peut pas être null")
    @Min(value = 1, message = "L'id de championnat ne peut être négatif")
    private Long championshipId;
}
