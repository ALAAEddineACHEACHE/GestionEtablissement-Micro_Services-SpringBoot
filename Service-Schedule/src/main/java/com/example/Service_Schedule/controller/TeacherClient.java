package com.example.Service_Schedule.controller;

import com.example.Service_Schedule.config.FeignSecurityConfig;
import com.example.Service_Schedule.dto.TeacherDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "SERVICE-TEACHER",
        url = "http://localhost:6003",
        configuration = FeignSecurityConfig.class
)
public interface TeacherClient {
    @Cacheable(value = "teachers", key = "#id")
    @GetMapping("/teachers/{id}")
    TeacherDTO getTeacherById(@PathVariable String id);
}
