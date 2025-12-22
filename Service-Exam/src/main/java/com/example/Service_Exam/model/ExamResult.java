package com.example.Service_Exam.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResult {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "score", nullable = false)
    private Double score;

    @Column(name = "grade")
    private String grade;

    @Column(name = "passed")
    private Boolean passed;

    @Column(name = "remarks", length = 1000)
    private String remarks;
}
