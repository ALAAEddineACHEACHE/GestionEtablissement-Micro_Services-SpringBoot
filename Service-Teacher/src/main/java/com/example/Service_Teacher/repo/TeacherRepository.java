package com.example.Service_Teacher.repo;

import com.example.Service_Teacher.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
