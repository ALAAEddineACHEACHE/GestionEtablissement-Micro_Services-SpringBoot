package com.example.Service_Exam.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private String id;
    private String firstName;
    private String lastName;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
