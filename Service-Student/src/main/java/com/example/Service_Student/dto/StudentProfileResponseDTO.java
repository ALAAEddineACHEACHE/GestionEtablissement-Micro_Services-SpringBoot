package com.example.Service_Student.dto;


import lombok.Data;
import java.util.List;

@Data
public class StudentProfileResponseDTO {
    private String id;
    private String name;
    private String email;
    private String level;

    // Résultats d'examens
    private List<StudentExamResultDTO> examResults;

    // Nombre total d'examens
    private Integer totalExams;
    private Integer passedExams;
    private Double averageScore;
    private Double successRate; // Pourcentage

    // Calculer la réussite
    public void calculateStats() {
        if (examResults != null && !examResults.isEmpty()) {
            this.totalExams = examResults.size();
            this.passedExams = (int) examResults.stream()
                    .filter(StudentExamResultDTO::getPassed)
                    .count();
            this.averageScore = examResults.stream()
                    .mapToDouble(StudentExamResultDTO::getScore)
                    .average()
                    .orElse(0.0);
            this.successRate = totalExams > 0 ?
                    (passedExams * 100.0) / totalExams : 0.0;
        } else {
            this.totalExams = 0;
            this.passedExams = 0;
            this.averageScore = 0.0;
            this.successRate = 0.0;
        }
    }
}