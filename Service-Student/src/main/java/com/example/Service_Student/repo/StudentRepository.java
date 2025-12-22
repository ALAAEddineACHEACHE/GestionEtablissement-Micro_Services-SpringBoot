package com.example.Service_Student.repo;

import com.example.Service_Student.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {
    //Optional<Student> findByKeycloakId(String keycloakId);

    Optional<Student> findByEmail(String email);
}
