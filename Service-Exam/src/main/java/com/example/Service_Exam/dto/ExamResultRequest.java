package com.example.Service_Exam.dto;

import lombok.Data;

@Data
public class ExamResultRequest {
    private String studentId;
    private Double score;
    private String remarks;
}

