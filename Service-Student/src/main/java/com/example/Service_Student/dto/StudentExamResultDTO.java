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
    private String examCode;
    private LocalDateTime examDate;
    private Double score;
    private Double maxScore;
    private Double percentage;
    private String grade;
    private Boolean passed;
    private String remarks;
    private String studentId; // Pour référence

    // Constructeur pour JPQL
    public StudentExamResultDTO(Long examId, String examName, String examCode,
                                LocalDateTime examDate, Double score, Double maxScore,
                                String grade, Boolean passed) {
        this.examId = examId;
        this.examName = examName;
        this.examCode = examCode;
        this.examDate = examDate;
        this.score = score;
        this.maxScore = maxScore;
        this.grade = grade;
        this.passed = passed;
        this.percentage = calculatePercentageValue(score, maxScore);
    }

    public void calculatePercentage() {
        this.percentage = calculatePercentageValue(score, maxScore);
    }

    private Double calculatePercentageValue(Double score, Double maxScore) {
        if (score != null && maxScore != null && maxScore > 0) {
            return (score / maxScore) * 100;
        }
        return 0.0;
    }
}