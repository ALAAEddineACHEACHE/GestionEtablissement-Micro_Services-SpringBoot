package com.example.Service_Exam.controller;

import com.example.Service_Exam.dto.StudentDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentClientFallback implements StudentClient {

    @Override
    public StudentDTO getStudentById(String id) {
        StudentDTO dto = new StudentDTO();
        dto.setId(id);
        dto.setName("STUDENT_UNAVAILABLE");
        return dto;
    }
}
