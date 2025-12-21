package com.example.Service_Schedule.controller;

import com.example.Service_Schedule.config.FeignSecurityConfig;
import com.example.Service_Schedule.dto.CourseDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "SERVICE-COURSE",
        url = "${feign.client.config.default.url:http://localhost:6004}",
        configuration = FeignSecurityConfig.class)

public interface CourseClient {
    @Cacheable(value = "courses", key = "#id")
    @GetMapping("/courses/{id}")
    CourseDTO getCourseById(@PathVariable Long id);

    // Optionnel: pour vérifier si le cours existe
    @GetMapping("/courses/{id}/exists")
    Boolean courseExists(@PathVariable Long id);
}