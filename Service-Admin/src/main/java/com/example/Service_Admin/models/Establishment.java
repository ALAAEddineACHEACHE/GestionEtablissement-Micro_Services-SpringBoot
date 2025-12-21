package com.example.Service_Admin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Establishment {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String academicYear;
}
