package com.example.Service_Exam.controller;

import com.example.Service_Exam.config.FeignClientConfig;
import com.example.Service_Exam.dto.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SERVICE-STUDENT", url = "${feign.client.config.student.url:http://localhost:6005}", configuration = FeignClientConfig.class)
public interface StudentClient {
    @GetMapping("/students/{id}")
    StudentDTO getStudentById(@PathVariable String id);
}
