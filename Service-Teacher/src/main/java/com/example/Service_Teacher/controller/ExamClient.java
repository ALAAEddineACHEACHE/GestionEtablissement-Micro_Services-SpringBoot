package com.example.Service_Teacher.controller;

import com.example.Service_Teacher.dto.TeacherExamDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "SERVICE-EXAM",
        configuration = FeignSecurityConfig.class
)
public interface ExamClient {

    @GetMapping("/exams/teacher/{teacherId}")
    List<TeacherExamDTO> getExamsByTeacher(
            @PathVariable String teacherId
    );
}
