package com.example.Service_Student.controller;


import com.example.Service_Student.dto.StudentExamResultDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamClientFallback implements ExamClient {



    @Override
    public List<StudentExamResultDTO> getStudentResultsByEmail(String email) {
        return List.of();
    }
}