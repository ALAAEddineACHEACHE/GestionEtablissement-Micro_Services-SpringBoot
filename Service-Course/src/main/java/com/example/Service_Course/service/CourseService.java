package com.example.Service_Course.service;

import com.example.Service_Course.models.Course;
import com.example.Service_Course.repo.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public Course save(Course course) {
        return repository.save(course);
    }

    public List<Course> findAll() {
        return repository.findAll();
    }

    public Course findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    public Course update(Long id, Course course) {
        Course existingCourse = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        // Mettre à jour uniquement les champs non nuls
        if (course.getCode() != null) {
            existingCourse.setCode(course.getCode());
        }
        if (course.getTitle() != null) {
            existingCourse.setTitle(course.getTitle());
        }
        if (course.getSemester() != null) {
            existingCourse.setSemester(course.getSemester());
        }
        if (course.getProgram() != null) {
            existingCourse.setProgram(course.getProgram());
        }
        if (course.getCredits() > 0) {
            existingCourse.setCredits(course.getCredits());
        }
        if (course.getTeacherId() != null) {
            existingCourse.setTeacherId(course.getTeacherId());
        }

        return repository.save(existingCourse);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        repository.deleteById(id);
    }
}