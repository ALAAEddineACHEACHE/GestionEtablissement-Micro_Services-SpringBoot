package com.example.Service_Exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentExamResultDTO {
    private Long examId;
    private String examName;
    private String examCode;
    private LocalDateTime examDate;
    private Double score;
    private Double maxScore;
    private Double percentage;
    private String grade;
    private Boolean passed;
    private String remarks;
    private String studentId;

    // Constructeur pour JPQL (COMPATIBLE AVEC VOTRE QUERY)
    public StudentExamResultDTO(Long examId, String examName, Double score,
                                String grade, Boolean passed) {
        this.examId = examId;
        this.examName = examName;
        this.score = score;
        this.grade = grade;
        this.passed = passed;
        this.examCode = ""; // Valeur par défaut
        this.examDate = null; // Valeur par défaut
        this.maxScore = 0.0; // Valeur par défaut
        calculatePercentage();
    }

    // AJOUTEZ CETTE MÉTHODE (pas calculateGrade mais calculatePercentage)
    public void calculatePercentage() {
        if (score != null && maxScore != null && maxScore > 0) {
            this.percentage = (score / maxScore) * 100;
        } else {
            this.percentage = 0.0;
        }
    }

    // Option: Ajoutez cette méthode si vous voulez calculer la grade
    public void calculateGradeFromPercentage() {
        if (percentage == null) {
            this.grade = "N/A";
            return;
        }
        if (percentage >= 90) this.grade = "A";
        else if (percentage >= 80) this.grade = "B";
        else if (percentage >= 70) this.grade = "C";
        else if (percentage >= 60) this.grade = "D";
        else this.grade = "F";
    }
}