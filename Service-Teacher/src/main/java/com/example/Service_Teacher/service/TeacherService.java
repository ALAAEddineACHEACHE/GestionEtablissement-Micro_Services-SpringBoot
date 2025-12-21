package com.example.Service_Teacher.service;

import com.example.Service_Teacher.models.Teacher;
import com.example.Service_Teacher.repo.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherService {

    private final TeacherRepository repository;

    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public List<Teacher> findAll() {
        return repository.findAll();
    }

    public Optional<Teacher> findById(Long id) {
        return repository.findById(id);
    }

    public Teacher save(Teacher teacher) {
        return repository.save(teacher);
    }

    public Teacher update(Long id, Teacher teacher) {
        Teacher existingTeacher = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));

        // Mettre à jour uniquement les champs non nuls
        if (teacher.getName() != null) {
            existingTeacher.setName(teacher.getName());
        }
        if (teacher.getDepartment() != null) {
            existingTeacher.setDepartment(teacher.getDepartment());
        }

        return repository.save(existingTeacher);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
        repository.deleteById(id);
    }
}