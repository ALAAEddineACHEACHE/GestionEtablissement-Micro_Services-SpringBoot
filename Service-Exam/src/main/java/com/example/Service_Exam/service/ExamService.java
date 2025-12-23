package com.example.Service_Exam.service;

import com.example.Service_Exam.controller.CourseClient;
import com.example.Service_Exam.controller.StudentClient;
import com.example.Service_Exam.dto.*;
import com.example.Service_Exam.model.Exam;
import com.example.Service_Exam.model.ExamResult;
import com.example.Service_Exam.repo.ExamRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.Service_Exam.dto.StudentDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
@Transactional
public class ExamService {

    private final ExamRepository repository;
    private final CourseClient courseClient;
    private final StudentClient studentClient;

    public ExamService(ExamRepository repository, CourseClient courseClient, StudentClient studentClient) {
        this.repository = repository;
        this.courseClient = courseClient;
        this.studentClient = studentClient;
    }

    public List<StudentExamResultDTO> getResultsByStudent(String studentId) {
        return repository.findResultsByStudentId(studentId);
    }
    public List<TeacherExamDTO> findExamsByTeacher(Long teacherId) {
        return repository.findByTeacherId(teacherId)
                .stream()
                .map(exam -> {
                    TeacherExamDTO dto = new TeacherExamDTO();
                    dto.setExamId(exam.getId());
                    dto.setName(exam.getName());
                    dto.setExamType(exam.getExamType());
                    dto.setStatus(exam.getStatus());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    // CREATE
    public ExamResponse create(ExamRequest request, Long teacherId) {
        Exam exam = new Exam();
        exam.setName(request.getName());
        exam.setCode(request.getCode());
        exam.setCourseId(request.getCourseId());
        exam.setExamDateTime(request.getExamDateTime());
        exam.setDurationMinutes(request.getDurationMinutes());
        exam.setRoom(request.getRoom());
        exam.setExamType(request.getExamType());
        exam.setDescription(request.getDescription());
        exam.setStatus("SCHEDULED");
        exam.setMaxScore(request.getMaxScore());
        exam.setTeacherId(teacherId); // <-- IMPORTANT

        Exam saved = repository.save(exam);
        return convertToResponse(saved);
    }


    // READ ALL
    public List<ExamResponse> findAll() {
        return repository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // READ BY ID
    public ExamResponse findById(Long id) {
        Exam exam = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + id));
        return convertToResponse(exam);
    }

    // READ BY COURSE
    public List<ExamResponse> findByCourseId(Long courseId) {
        return repository.findByCourseId(courseId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // READ BY STATUS
    public List<ExamResponse> findByStatus(String status) {
        return repository.findByStatus(status).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // READ UPCOMING EXAMS
    public List<ExamResponse> findUpcomingExams() {
        return repository.findByExamDateTimeAfter(LocalDateTime.now()).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // UPDATE
    public ExamResponse update(Long id, ExamRequest request) {
        Exam exam = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + id));

        exam.setName(request.getName());
        exam.setCode(request.getCode());
        exam.setCourseId(request.getCourseId());
        exam.setExamDateTime(request.getExamDateTime());
        exam.setDurationMinutes(request.getDurationMinutes());
        exam.setRoom(request.getRoom());
        exam.setExamType(request.getExamType());
        exam.setDescription(request.getDescription());
        exam.setMaxScore(request.getMaxScore());

        Exam updated = repository.save(exam);
        return convertToResponse(updated);
    }

    // UPDATE STATUS
    public ExamResponse updateStatus(Long id, String status) {
        Exam exam = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + id));

        exam.setStatus(status);
        Exam updated = repository.save(exam);
        return convertToResponse(updated);
    }

    // ADD RESULT
    public ExamResponse addResult(Long examId, ExamResultRequest resultRequest) {
        Exam exam = repository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));

        Double score = resultRequest.getScore();
        String grade = calculateGrade(score, exam.getMaxScore());
        Boolean passed = score >= (exam.getMaxScore() * 0.5);

        ExamResult result = new ExamResult(
                resultRequest.getStudentId(),
                score,
                grade,
                passed,
                resultRequest.getRemarks()
        );

        exam.getResults().add(result);
        Exam updated = repository.save(exam);
        return convertToResponse(updated);
    }

    // GET RESULTS
    public List<ExamResultResponse> getResults(Long examId) {
        Exam exam = repository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));

        return exam.getResults().stream()
                .map(result -> {
                    ExamResultResponse response = new ExamResultResponse();
                    response.setStudentId(result.getStudentId());

                    try {
                        StudentDTO student = studentClient.getStudentById(result.getStudentId());
                        response.setStudentName(student.getFullName());
                    } catch (Exception ex) {
                        // 🔴 LOG IMPORTANT
                        log.warn(
                                "Impossible de récupérer l'étudiant avec id={} pour examId={}",
                                result.getStudentId(),
                                examId,
                                ex
                        );

                        // Fallback fonctionnel
                        response.setStudentName("STUDENT_NOT_FOUND");
                    }

                    response.setScore(result.getScore());
                    response.setGrade(result.getGrade());
                    response.setPassed(result.getPassed());
                    response.setRemarks(result.getRemarks());

                    return response;
                })
                .collect(Collectors.toList());
    }


    // DELETE
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Exam not found with id: " + id);
        }
        repository.deleteById(id);
    }

    // UTIL
    private String calculateGrade(Double score, Double maxScore) {
        if (score == null || maxScore == null) return "N/A";
        double percentage = (score / maxScore) * 100;
        if (percentage >= 90) return "A";
        if (percentage >= 80) return "B";
        if (percentage >= 70) return "C";
        if (percentage >= 60) return "D";
        return "F";
    }

    private ExamResponse convertToResponse(Exam exam) {
        ExamResponse response = new ExamResponse();
        response.setId(exam.getId());
        response.setName(exam.getName());
        response.setCode(exam.getCode());
        response.setCourseId(exam.getCourseId());

        // Récupération via Feign Client
        try {
            response.setCourseTitle(courseClient.getCourseById(exam.getCourseId()).getTitle());
        } catch (Exception e) {
            response.setCourseTitle("Course Service Unavailable");
        }

        response.setExamDateTime(exam.getExamDateTime());
        response.setDurationMinutes(exam.getDurationMinutes());
        response.setRoom(exam.getRoom());
        response.setExamType(exam.getExamType());
        response.setDescription(exam.getDescription());
        response.setStatus(exam.getStatus());
        response.setMaxScore(exam.getMaxScore());
        return response;
    }
}
