package com.example.Service_Schedule.dto;

import lombok.Data;

@Data
public class CourseDTO {
    private Long id;
    private String code;
    private String title;        // ✅ Doit être présent
    private String semester;
    private String program;
    private Integer credits;
    private Long teacherId;

    // Assurez-vous d'avoir getTitle()
    public String getTitle() {
        return title;
    }
}