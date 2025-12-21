package com.example.Service_Course.repo;


import com.example.Service_Course.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
