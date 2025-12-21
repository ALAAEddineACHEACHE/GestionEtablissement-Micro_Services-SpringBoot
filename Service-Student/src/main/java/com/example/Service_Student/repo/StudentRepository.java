package com.example.Service_Student.repo;

import com.example.Service_Student.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
