package com.example.Service_Student.controller;

import com.example.Service_Student.dto.StudentExamResultDTO;
import com.example.Service_Student.models.Student;
import com.example.Service_Student.repo.StudentRepository;
import com.example.Service_Student.service.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;
    private final StudentRepository studentRepository;
    private final ExamClient examClient;

    public StudentController(StudentService service, StudentRepository studentRepository,ExamClient examClient) {
        this.service = service;
        this.studentRepository = studentRepository;
        this.examClient = examClient;

    }

    // GET → USER ou ADMIN

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Student> all() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN','STUDENT')")
    public Student getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    public Student create(@RequestBody Student student) {
        return service.save(student);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Student updateStudent(@PathVariable String id, @RequestBody Student student) {
        return service.update(id, student);
    }
    @GetMapping("/my-results")
    @PreAuthorize("hasRole('STUDENT')")
    public List<StudentExamResultDTO> getMyResults(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String email = jwt.getClaimAsString("email");

        // Vérifier que l'étudiant existe
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // ✅ Passer l'email directement au service Exam
        return examClient.getStudentResultsByEmail(email);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

}