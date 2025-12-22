package com.example.Service_Exam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "exams")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;  // "Examen Final Mathématiques"

    @Column(nullable = false)
    private String code;  // "MATH-FINAL-2024"

    @Column(nullable = false)
    private Long courseId;  // Référence au cours

    @Column(nullable = false)
    private LocalDateTime examDateTime;  // Date et heure précise

    @Column(nullable = false)
    private Integer durationMinutes;  // Durée en minutes (90, 120, 180)

    @Column(nullable = false)
    private String room;  // Salle d'examen

    @Column(nullable = false)
    private String examType;  // "FINAL", "MIDTERM", "QUIZ", "PROJECT"

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String status;  // "SCHEDULED", "IN_PROGRESS", "COMPLETED", "CANCELLED"

    @Column
    private Double maxScore;  // Note maximale (ex: 20)

    // Pour les résultats
    @ElementCollection
    @CollectionTable(name = "exam_results", joinColumns = @JoinColumn(name = "exam_id"))
    private List<ExamResult> results;
}
