package com.example.Service_Course.models;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String semester;

    @Column(nullable = false)
    private String program;

    @Column(nullable = false)
    private int credits;

    @Column(name = "teacher_id")
    private Long teacherId;

    // Constructeurs
    public Course() {}

    public Course(String code, String title, String semester, String program, int credits, Long teacherId) {
        this.code = code;
        this.title = title;
        this.semester = semester;
        this.program = program;
        this.credits = credits;
        this.teacherId = teacherId;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public Long getTeacherId() { return teacherId; }
    public void setTeacherId(Long teacherId) { this.teacherId = teacherId; }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", semester='" + semester + '\'' +
                ", program='" + program + '\'' +
                ", credits=" + credits +
                ", teacherId=" + teacherId +
                '}';
    }
}