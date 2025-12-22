package com.example.Service_Student.dto;
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
    private String courseCode;
    private LocalDateTime examDate;
    private Double score;
    private Double maxScore;
    private Double percentage;
    private String grade;
    private Boolean passed;
    private String remarks;

    // Constructeur pour JPQL (si nécessaire)
    public StudentExamResultDTO(Long examId, String examName, Double score, String grade, Boolean passed) {
        this.examId = examId;
        this.examName = examName;
        this.score = score;
        this.grade = grade;
        this.passed = passed;
        this.percentage = 0.0; // À calculer après
    }

    // Méthode pour calculer le pourcentage
    public void calculatePercentage() {
        if (maxScore != null && maxScore > 0 && score != null) {
            this.percentage = (score / maxScore) * 100;
        } else {
            this.percentage = 0.0;
        }
    }
}