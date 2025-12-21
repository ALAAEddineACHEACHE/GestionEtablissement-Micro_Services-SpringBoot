package com.example.Service_Schedule.dto;

import lombok.Data;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class ScheduleRequest {
    private Long courseId;  // Renommez en courseId
    private String teacherId;
    private String room;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;

    public Long getCourse() {
        return 0L;
    }
}