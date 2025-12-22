package com.example.Service_Exam.repo;

import com.example.Service_Exam.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
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
}