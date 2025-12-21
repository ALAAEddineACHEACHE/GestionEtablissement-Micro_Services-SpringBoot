package com.example.Service_Schedule.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class ScheduleResponse {
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

    // Getters
    public Long getId() { return id; }
    public Long getCourseId() { return courseId; }
    public String getCourseTitle() { return courseTitle; }
    public String getTeacherId() { return teacherId; }
    public String getTeacherName() { return teacherName; }
    public String getRoom() { return room; }
    public DayOfWeek getDay() { return day; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public String getDescription() { return description; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }
    public void setRoom(String room) { this.room = room; }
    public void setDay(DayOfWeek day) { this.day = day; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public void setDescription(String description) { this.description = description; }

    public String getTimeSlot() {
        return startTime + " - " + endTime;
    }
}