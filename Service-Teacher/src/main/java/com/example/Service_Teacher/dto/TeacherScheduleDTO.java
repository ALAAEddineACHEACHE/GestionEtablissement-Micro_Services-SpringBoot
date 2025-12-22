package com.example.Service_Teacher.dto;

import lombok.Data;

@Data
public class TeacherScheduleDTO {
    private Long scheduleId;
    private String courseName;
    private String room;
    private String startTime;
    private String endTime;
}

