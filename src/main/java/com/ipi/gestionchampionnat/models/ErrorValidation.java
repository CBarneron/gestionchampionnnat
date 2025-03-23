package com.ipi.gestionchampionnat.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorValidation {
    private String input;
    private String error;

    public ErrorValidation(String input, String error) {
        this.input = input;
        this.error = error;
    }
}
