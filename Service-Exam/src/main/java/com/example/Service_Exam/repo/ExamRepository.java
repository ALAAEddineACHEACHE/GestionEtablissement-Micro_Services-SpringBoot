package com.example.Service_Exam.repo;

import com.example.Service_Exam.dto.StudentExamResultDTO;
import com.example.Service_Exam.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findByCourseId(Long courseId);

    List<Exam> findByStatus(String status);

    List<Exam> findByExamDateTimeAfter(LocalDateTime dateTime);

    List<Exam> findByRoomAndExamDateTimeBetween(String room, LocalDateTime start, LocalDateTime end);

    List<Exam> findByExamType(String examType);

    @Query("SELECT NEW com.example.Service_Exam.dto.StudentExamResultDTO(" +
            "e.id, e.name, r.score, r.grade, r.passed) " +
            "FROM Exam e JOIN e.results r " +
            "WHERE r.studentId = :studentId")
    List<StudentExamResultDTO> findResultsByStudentId(@Param("studentId") String studentId);
    List<Exam> findByTeacherId(Long teacherId);

}