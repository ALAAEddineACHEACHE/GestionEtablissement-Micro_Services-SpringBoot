package com.example.Service_Teacher.controller;

import com.example.Service_Teacher.dto.TeacherScheduleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
@FeignClient(
        name = "SERVICE-SCHEDULE",
        configuration = FeignSecurityConfig.class
)
public interface ScheduleClient {

    @GetMapping("/schedules/teacher/{teacherId}")
    List<TeacherScheduleDTO> getSchedulesByTeacher(
            @PathVariable String teacherId
    );
}
