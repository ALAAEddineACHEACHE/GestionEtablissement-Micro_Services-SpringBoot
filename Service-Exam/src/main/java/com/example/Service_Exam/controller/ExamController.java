package com.example.Service_Exam.controller;

import com.example.Service_Exam.dto.ExamRequest;
import com.example.Service_Exam.dto.ExamResponse;
import com.example.Service_Exam.dto.ExamResultRequest;
import com.example.Service_Exam.dto.ExamResultResponse;
import com.example.Service_Exam.service.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    // GET all exams
    @GetMapping
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT', 'ADMIN')")
    public ResponseEntity<List<ExamResponse>> getAllExams() {
        List<ExamResponse> exams = examService.findAll();
        return ResponseEntity.ok(exams);
    }

    // GET exam by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT', 'ADMIN')")
    public ResponseEntity<ExamResponse> getExamById(@PathVariable Long id) {
        ExamResponse exam = examService.findById(id);
        return ResponseEntity.ok(exam);
    }

    // GET exams by course
    @GetMapping("/course/{courseId}")
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT', 'ADMIN')")
    public ResponseEntity<List<ExamResponse>> getExamsByCourse(@PathVariable Long courseId) {
        List<ExamResponse> exams = examService.findByCourseId(courseId);
        return ResponseEntity.ok(exams);
    }

    // GET exams by status
    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<List<ExamResponse>> getExamsByStatus(@PathVariable String status) {
        List<ExamResponse> exams = examService.findByStatus(status);
        return ResponseEntity.ok(exams);
    }

    // GET upcoming exams
    @GetMapping("/upcoming")
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT', 'ADMIN')")
    public ResponseEntity<List<ExamResponse>> getUpcomingExams() {
        List<ExamResponse> exams = examService.findUpcomingExams();
        return ResponseEntity.ok(exams);
    }

    // POST create exam
    @PostMapping
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<ExamResponse> createExam(@RequestBody ExamRequest request) {
        ExamResponse created = examService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT update exam
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<ExamResponse> updateExam(
            @PathVariable Long id,
            @RequestBody ExamRequest request) {
        ExamResponse updated = examService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    // PATCH update exam status
    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<ExamResponse> updateExamStatus(
            @PathVariable Long id,
            @RequestBody String status) {
        ExamResponse updated = examService.updateStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    // POST add exam result
    @PostMapping("/{id}/results")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<ExamResponse> addExamResult(
            @PathVariable Long id,
            @RequestBody ExamResultRequest resultRequest) {
        ExamResponse updated = examService.addResult(id, resultRequest);
        return ResponseEntity.ok(updated);
    }

    // GET exam results
    @GetMapping("/{id}/results")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<List<ExamResultResponse>> getExamResults(@PathVariable Long id) {
        List<ExamResultResponse> results = examService.getResults(id);
        return ResponseEntity.ok(results);
    }

    // DELETE exam
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Exception handler
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}