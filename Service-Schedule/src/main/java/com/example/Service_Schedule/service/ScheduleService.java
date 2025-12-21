package com.example.Service_Schedule.service;

import com.example.Service_Schedule.controller.CourseClient;
import com.example.Service_Schedule.dto.CourseDTO;
import com.example.Service_Schedule.dto.ScheduleDTO;
import com.example.Service_Schedule.dto.ScheduleRequest;
import com.example.Service_Schedule.models.Schedule;
import com.example.Service_Schedule.repo.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository repository;
    private final CourseClient courseClient;

    public ScheduleService(ScheduleRepository repository, CourseClient courseClient) {
        this.repository = repository;
        this.courseClient = courseClient;
    }

    // CREATE
    public ScheduleDTO create(ScheduleRequest request) {
        // DÉBOGAGE: Vérifiez ce que contient la requête
        System.out.println("Creating schedule with course: " + request.getCourse());

        // Validation: vérifier si le cours existe
        CourseDTO course = courseClient.getCourseById(request.getCourse());  // Notez getCourse()
        if (course == null) {
            throw new IllegalArgumentException("Course with id " + request.getCourse() + " not found");
        }

        // Validation: vérifier les conflits de salle
        if (isRoomOccupied(request.getRoom(), request.getDay(),
                request.getStartTime(), request.getEndTime())) {
            throw new IllegalArgumentException("Room " + request.getRoom() +
                    " is already occupied at this time slot");
        }

        // Création du schedule
        Schedule schedule = new Schedule(
                request.getCourse(),  // Notez getCourse()
                request.getTeacherId(),
                request.getRoom(),
                request.getDay(),
                request.getStartTime(),
                request.getEndTime(),
                request.getDescription()
        );

        Schedule saved = repository.save(schedule);
        return convertToDTO(saved, course);
    }

    // READ - Tous
    public List<ScheduleDTO> findAll() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // READ - Par ID
    public ScheduleDTO findById(Long id) {
        Schedule schedule = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));

        CourseDTO course = courseClient.getCourseById(schedule.getCourseId());
        return convertToDTO(schedule, course);
    }

    // READ - Par cours
    public List<ScheduleDTO> findByCourseId(Long courseId) {
        List<Schedule> schedules = repository.findByCourseId(courseId);
        CourseDTO course = courseClient.getCourseById(courseId);

        return schedules.stream()
                .map(schedule -> convertToDTO(schedule, course))
                .collect(Collectors.toList());
    }

    // READ - Par enseignant
    public List<ScheduleDTO> findByTeacherId(String teacherId) {
        List<Schedule> schedules = repository.findByTeacherId(teacherId);

        return schedules.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // UPDATE
    public ScheduleDTO update(Long id, ScheduleRequest request) {
        Schedule schedule = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));

        // Validation du cours
        CourseDTO course = courseClient.getCourseById(request.getCourse());  // Notez getCourse()
        if (course == null) {
            throw new IllegalArgumentException("Course with id " + request.getCourse() + " not found");
        }

        // Vérifier si c'est le même schedule
        boolean isSameSchedule = schedule.getId().equals(id) &&
                schedule.getRoom().equals(request.getRoom()) &&
                schedule.getDay().equals(request.getDay()) &&
                schedule.getStartTime().equals(request.getStartTime()) &&
                schedule.getEndTime().equals(request.getEndTime());

        // Validation des conflits de salle
        if (!isSameSchedule && isRoomOccupied(request.getRoom(), request.getDay(),
                request.getStartTime(), request.getEndTime())) {
            throw new IllegalArgumentException("Room " + request.getRoom() +
                    " is already occupied at this time slot");
        }

        // Mise à jour
        schedule.setCourseId(request.getCourse());  // Notez getCourse()
        schedule.setTeacherId(request.getTeacherId());
        schedule.setRoom(request.getRoom());
        schedule.setDay(request.getDay());
        schedule.setStartTime(request.getStartTime());
        schedule.setEndTime(request.getEndTime());
        schedule.setDescription(request.getDescription());

        Schedule updated = repository.save(schedule);
        return convertToDTO(updated, course);
    }

    // DELETE
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Schedule not found with id: " + id);
        }
        repository.deleteById(id);
    }

    // Utilitaires
    private boolean isRoomOccupied(String room, DayOfWeek day,
                                   LocalTime startTime, LocalTime endTime) {
        List<Schedule> existing = repository.findByRoomAndDay(room, day);
        return existing.stream().anyMatch(schedule ->
                schedule.getStartTime().isBefore(endTime) &&
                        schedule.getEndTime().isAfter(startTime)
        );
    }

    private ScheduleDTO convertToDTO(Schedule schedule) {
        try {
            CourseDTO course = courseClient.getCourseById(schedule.getCourseId());
            return convertToDTO(schedule, course);
        } catch (Exception e) {
            // Fallback si le service Course n'est pas disponible
            return createFallbackDTO(schedule);
        }
    }

    private ScheduleDTO convertToDTO(Schedule schedule, CourseDTO course) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setCourseId(schedule.getCourseId());
        dto.setCourseTitle(course != null ? course.getTitle() : "Unknown Course");
        dto.setTeacherId(schedule.getTeacherId());
        dto.setRoom(schedule.getRoom());
        dto.setDay(schedule.getDay());
        dto.setStartTime(schedule.getStartTime());
        dto.setEndTime(schedule.getEndTime());
        dto.setDescription(schedule.getDescription());
        return dto;
    }

    private ScheduleDTO createFallbackDTO(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setCourseId(schedule.getCourseId());
        dto.setCourseTitle("Course Service Unavailable");
        dto.setTeacherId(schedule.getTeacherId());
        dto.setRoom(schedule.getRoom());
        dto.setDay(schedule.getDay());
        dto.setStartTime(schedule.getStartTime());
        dto.setEndTime(schedule.getEndTime());
        dto.setDescription(schedule.getDescription());
        return dto;
    }
}