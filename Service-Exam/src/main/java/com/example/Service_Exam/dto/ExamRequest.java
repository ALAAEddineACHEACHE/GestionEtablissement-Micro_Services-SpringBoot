// ExamRequest.java
package com.example.Service_Exam.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ExamRequest {
    private String name;
    private String code;
    private Long courseId;
    private LocalDateTime examDateTime;
    private Integer durationMinutes;
    private String room;
    private String examType;
    private String description;
    private Double maxScore;
    private Long teacherId; // ← Ajoutez ce champ

}