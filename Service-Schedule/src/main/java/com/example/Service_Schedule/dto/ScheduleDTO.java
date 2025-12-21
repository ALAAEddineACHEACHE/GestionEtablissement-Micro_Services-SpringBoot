package com.example.Service_Schedule.dto;

import lombok.Data;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class ScheduleDTO {
    private Long id;
    private Long courseId;
    private String courseTitle;
    private String teacherId;
    private String teacherName;
    private String room;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;

    public String getTimeSlot() {
        return startTime + " - " + endTime;
    }
}