// ExamResultResponse.java
package com.example.Service_Exam.dto;

import lombok.Data;

@Data
public class ExamResultResponse {
    private String studentId;
    private String studentName;
    private Double score;
    private String grade;
    private Boolean passed;
    private String remarks;
}