package com.ipi.gestionchampionnat.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
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

    @Column(nullable = false,length = 50)
    @NotNull(message = "Le champ firstname ne peut être null")
    @NotBlank( message = "Le champ firstname ne peut être vide")
    private String firstName;
    
    @Column(nullable = false,length = 50)
    @NotNull(message = "Le champ lastname ne peut être null")
    @NotBlank( message = "Le champ lastname ne peut être vide") 
    private String lastName;

    @Column(nullable = false,length = 50)
    @NotNull(message = "Le champ email ne peut être null")
    @NotBlank( message = "Le champ email ne peut être vide")
    private String email;
    
    @Column(nullable = false,length = 50)
    @NotNull(message = "Le champ password ne peut être null")
    @NotBlank( message = "Le champ password ne peut être vide")
    private String password;

    @NotNull(message = "Le champ creationDate ne peut pas être null")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;
}
