package com.example.Service_Exam.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private String id;
    private String name;
    private String email;
    private String level;

    public String getFullName() {
        return name;
    }
}
