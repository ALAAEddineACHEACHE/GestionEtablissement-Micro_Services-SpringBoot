package com.example.Service_Schedule.controller;

import com.example.Service_Schedule.dto.CourseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SERVICE-COURSE", url = "${feign.client.config.default.url:http://localhost:6004}")
public interface CourseClient {

    @GetMapping("/courses/{id}")
    CourseDTO getCourseById(@PathVariable Long id);

    // Optionnel: pour vérifier si le cours existe
    @GetMapping("/courses/{id}/exists")
    Boolean courseExists(@PathVariable Long id);
}