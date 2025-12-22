package com.example.Service_Exam.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ExamResponse {
    private Long id;
    private String name;
    private String code;
    private Long courseId;
    private String courseTitle;
    private LocalDateTime examDateTime;
    private Integer durationMinutes;
    private String room;
    private String examType;
    private String description;
    private String status;
    private Double maxScore;
}
