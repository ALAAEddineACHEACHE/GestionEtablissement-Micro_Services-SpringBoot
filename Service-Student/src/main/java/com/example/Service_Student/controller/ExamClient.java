package com.example.Service_Student.controller;

import com.example.Service_Student.dto.StudentExamResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "SERVICE-EXAM")
public interface ExamClient {

    @GetMapping("/exams/students/{studentId}/results")
    List<StudentExamResultDTO> getStudentResults(@PathVariable String studentId);
}