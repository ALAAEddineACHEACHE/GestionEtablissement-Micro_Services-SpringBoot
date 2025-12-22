package com.example.Service_Teacher.controller;

import com.example.Service_Teacher.dto.TeacherExamDTO;
import com.example.Service_Teacher.dto.TeacherScheduleDTO;
import com.example.Service_Teacher.models.Teacher;
import com.example.Service_Teacher.service.TeacherService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','TEACHER','ADMIN')")
    public List<Teacher> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','TEACHER','ADMIN')")
    public Teacher getById(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Teacher create(@RequestBody Teacher teacher) {
        return service.save(teacher);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    public Teacher update(@PathVariable Long id, @RequestBody Teacher teacher) {
        return service.update(id, teacher);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // ======================
    // PROFIL
    // ======================
    @GetMapping("/{id}/profile")
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public Teacher getProfile(@PathVariable Long id) {
        return service.getProfile(id);
    }

    // ======================
    // CRENEAUX
    // ======================
    @GetMapping("/{id}/schedules")
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public List<TeacherScheduleDTO> getSchedules(@PathVariable Long id) {
        return service.getSchedules(id);
    }

    // ======================
    // EXAMENS
    // ======================
    @GetMapping("/{id}/exams")
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public List<TeacherExamDTO> getExams(@PathVariable Long id) {
        return service.getExams(id);
    }
}