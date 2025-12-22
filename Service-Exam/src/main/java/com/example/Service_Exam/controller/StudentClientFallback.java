package com.example.Service_Exam.controller;

import com.example.Service_Exam.dto.StudentDTO;
import com.example.Service_Exam.dto.StudentExamResultDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentClientFallback implements StudentClient {

    @Override
    public StudentDTO getStudentById(String id) {
        StudentDTO dto = new StudentDTO();
        dto.setId(id);
        dto.setName("STUDENT_UNAVAILABLE");
        return dto;
    }

    @Override
    public List<StudentExamResultDTO> getResultsByStudent(String studentId) {
        return List.of();
    }
}
