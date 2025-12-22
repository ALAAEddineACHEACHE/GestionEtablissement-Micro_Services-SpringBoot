package com.example.Service_Exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentExamResultDTO {
    private Long examId;
    private String examName;
    private Double score;
    private String grade;
    private Boolean passed;

    // ✅ Make sure you have this constructor for the JPQL query
    public StudentExamResultDTO(Long examId, String examName, Double score, String grade, Boolean passed) {
        this.examId = examId;
        this.examName = examName;
        this.score = score;
        this.grade = grade;
        this.passed = passed;
    }
}