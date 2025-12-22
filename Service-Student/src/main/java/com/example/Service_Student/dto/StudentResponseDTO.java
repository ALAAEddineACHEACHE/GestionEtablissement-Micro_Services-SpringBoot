package com.example.Service_Student.dto;


import com.example.Service_Student.models.Student;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StudentResponseDTO {
    private String id;
    private String name;
    private String email;
    private String level;
    private String keycloakId;

    // Dates système (si vous les avez dans l'entité)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Méthode utilitaire pour convertir depuis l'entité
    public static StudentResponseDTO fromEntity(Student student) {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setLevel(student.getLevel());
        dto.setKeycloakId(student.getKeycloakId());
        // Si vous avez des dates dans Student:
        // dto.setCreatedAt(student.getCreatedAt());
        // dto.setUpdatedAt(student.getUpdatedAt());
        return dto;
    }
}