package com.example.Service_Schedule.controller;

import com.example.Service_Schedule.dto.ScheduleDTO;
import com.example.Service_Schedule.dto.ScheduleRequest;
import com.example.Service_Schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // GET all schedules
    @GetMapping
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT', 'ADMIN')")
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        List<ScheduleDTO> schedules = scheduleService.findAll();
        return ResponseEntity.ok(schedules);
    }

    // GET schedule by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT', 'ADMIN')")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable Long id) {
        ScheduleDTO schedule = scheduleService.findById(id);
        return ResponseEntity.ok(schedule);
    }

    // GET schedules by course
    @GetMapping("/course/{courseId}")
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT', 'ADMIN')")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByCourse(@PathVariable Long courseId) {
        List<ScheduleDTO> schedules = scheduleService.findByCourseId(courseId);
        return ResponseEntity.ok(schedules);
    }

    // GET schedules by teacher
    @GetMapping("/teacher/{teacherId}")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByTeacher(@PathVariable String teacherId) {
        List<ScheduleDTO> schedules = scheduleService.findByTeacherId(teacherId);
        return ResponseEntity.ok(schedules);
    }

    // POST create schedule
    @PostMapping
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody ScheduleRequest request) {
        ScheduleDTO created = scheduleService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT update schedule
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<ScheduleDTO> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequest request) {
        ScheduleDTO updated = scheduleService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    // DELETE schedule
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Exception handler
    @ExceptionHandler({IllegalArgumentException.class, RuntimeException.class})
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}