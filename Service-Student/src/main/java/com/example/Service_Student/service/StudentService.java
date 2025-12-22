package com.example.Service_Student.service;

import com.example.Service_Student.models.Student;
import com.example.Service_Student.repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student save(Student student) {
        return repository.save(student);
    }

    public Student update(String id, Student s) {
        Student existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existing.setName(s.getName());
        existing.setEmail(s.getEmail());
        existing.setLevel(s.getLevel());

        return repository.save(existing);
    }
    public Student getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}


