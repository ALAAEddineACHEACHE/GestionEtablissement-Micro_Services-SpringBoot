package com.example.Service_Student.controller;

import com.example.Service_Student.models.Student;
import com.example.Service_Student.repo.StudentRepository;
import com.example.Service_Student.service.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // GET → USER ou ADMIN

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Student> all() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Student getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Student create(@RequestBody Student student) {
        return service.save(student);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Student updateStudent(@PathVariable String id, @RequestBody Student student) {
        return service.update(id, student);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

}