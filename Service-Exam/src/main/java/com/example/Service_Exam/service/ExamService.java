package com.example.Service_Exam.service;


import com.example.Service_Exam.model.Exam;
import com.example.Service_Exam.repo.ExamRepository;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

    private final ExamRepository repository;

    public ExamService(ExamRepository repository) {
        this.repository = repository;
    }

    public Exam save(Exam exam) {
        return repository.save(exam);
    }
}
