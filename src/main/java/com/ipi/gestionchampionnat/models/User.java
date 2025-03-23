package com.ipi.gestionchampionnat.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le champ firstname ne peut être null")
    @NotBlank( message = "Le champ firstname ne peut être vide")
    private String firstName;
    
    @NotNull(message = "Le champ lastname ne peut être null")
    @NotBlank( message = "Le champ lastname ne peut être vide") 
    private String lastName;

    @NotNull(message = "Le champ email ne peut être null")
    @NotBlank( message = "Le champ email ne peut être vide")
    private String email;
    
    @NotNull(message = "Le champ password ne peut être null")
    @NotBlank( message = "Le champ password ne peut être vide")
    private String password;

    @NotNull(message = "Le champ creationDate ne peut pas être null")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate = LocalDate.now();

    /**
     * Constructeur par défaut
     *
     */
    public User() {}

    /**
     * Constructeur avec paramètres
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param creationDate
     */
    public User(String firstName, String lastName, String email, String password, LocalDate creationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creationDate = creationDate;
    }

}
