package com.example.Service_Student.dto;


import lombok.Data;


@Data
public class StudentRequestDTO {

    private String name;

    private String email;

    private String level; // "L1", "L2", "L3", "M1", "M2"

    // Optionnel: Pour lier avec Keycloak (à utiliser par l'admin)
    private String keycloakId;
}