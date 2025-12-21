package com.example.Service_Schedule.service;

import com.example.Service_Schedule.controller.CourseClient;
import com.example.Service_Schedule.controller.TeacherClient;
import com.example.Service_Schedule.dto.CourseDTO;
import com.example.Service_Schedule.dto.ScheduleRequest;
import com.example.Service_Schedule.dto.ScheduleResponse;
import com.example.Service_Schedule.dto.TeacherDTO;
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
    private final TeacherClient teacherClient;

    public ScheduleService(
            ScheduleRepository repository,
            CourseClient courseClient,
            TeacherClient teacherClient
    ) {
        this.repository = repository;
        this.courseClient = courseClient;
        this.teacherClient = teacherClient;
    }

    // ===================== CREATE =====================
    public ScheduleResponse create(ScheduleRequest request) {

        CourseDTO course = courseClient.getCourseById(request.getCourseId());
        if (course == null) {
            throw new IllegalArgumentException("Course not found");
        }

        if (isRoomOccupied(
                request.getRoom(),
                request.getDay(),
                request.getStartTime(),
                request.getEndTime())) {
            throw new IllegalArgumentException("Room already occupied");
        }

        Schedule schedule = new Schedule(
                request.getCourseId(),
                request.getTeacherId(),
                request.getRoom(),
                request.getDay(),
                request.getStartTime(),
                request.getEndTime(),
                request.getDescription()
        );

        return convertToResponse(repository.save(schedule));
    }

    // ===================== READ =====================
    public List<ScheduleResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public ScheduleResponse findById(Long id) {
        Schedule schedule = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        return convertToResponse(schedule);
    }

    public List<ScheduleResponse> findByCourseId(Long courseId) {
        return repository.findByCourseId(courseId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public List<ScheduleResponse> findByTeacherId(String teacherId) {
        return repository.findByTeacherId(teacherId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // ===================== UPDATE =====================
    public ScheduleResponse update(Long id, ScheduleRequest request) {

        Schedule schedule = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        CourseDTO course = courseClient.getCourseById(request.getCourseId());
        if (course == null) {
            throw new IllegalArgumentException("Course not found");
        }

        if (isRoomOccupied(
                request.getRoom(),
                request.getDay(),
                request.getStartTime(),
                request.getEndTime())) {
            throw new IllegalArgumentException("Room already occupied");
        }

        schedule.setCourseId(request.getCourseId());
        schedule.setTeacherId(request.getTeacherId());
        schedule.setRoom(request.getRoom());
        schedule.setDay(request.getDay());
        schedule.setStartTime(request.getStartTime());
        schedule.setEndTime(request.getEndTime());
        schedule.setDescription(request.getDescription());

        return convertToResponse(repository.save(schedule));
    }

    // ===================== DELETE =====================
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Schedule not found");
        }
        repository.deleteById(id);
    }

    // ===================== UTIL =====================
    private boolean isRoomOccupied(
            String room,
            DayOfWeek day,
            LocalTime start,
            LocalTime end) {

        return repository.findByRoomAndDay(room, day)
                .stream()
                .anyMatch(s ->
                        s.getStartTime().isBefore(end)
                                && s.getEndTime().isAfter(start)
                );
    }

    // ===================== MAPPING =====================
    private ScheduleResponse convertToResponse(Schedule schedule) {

        CourseDTO course = null;
        TeacherDTO teacher = null;

        try {
            course = courseClient.getCourseById(schedule.getCourseId());
        } catch (Exception ignored) {}

        try {
            teacher = teacherClient.getTeacherById(schedule.getTeacherId());
        } catch (Exception ignored) {}

        ScheduleResponse response = new ScheduleResponse();
        response.setId(schedule.getId());
        response.setCourseId(schedule.getCourseId());
        response.setCourseTitle(course != null ? course.getTitle() : "Unknown Course");
        response.setTeacherId(schedule.getTeacherId());
        response.setTeacherName(teacher != null ? teacher.getName() : "Unknown Teacher");
        response.setRoom(schedule.getRoom());
        response.setDay(schedule.getDay());
        response.setStartTime(schedule.getStartTime());
        response.setEndTime(schedule.getEndTime());
        response.setDescription(schedule.getDescription());

        return response;
    }
}
