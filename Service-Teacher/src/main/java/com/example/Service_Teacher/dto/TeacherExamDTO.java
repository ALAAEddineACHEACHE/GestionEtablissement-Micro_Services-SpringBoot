package com.example.Service_Teacher.dto;

import lombok.Data;

@Data
public class TeacherExamDTO {
    private Long examId;
    private String name;
    private String examType;
    private String status;
}
