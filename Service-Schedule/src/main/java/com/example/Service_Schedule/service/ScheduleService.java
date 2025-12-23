package com.example.Service_Schedule.service;

import com.example.Service_Schedule.controller.CourseClient;
import com.example.Service_Schedule.controller.TeacherClient;
import com.example.Service_Schedule.dto.*;
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
    //Mapping CORRECT vers TeacherScheduleDTO
    private TeacherScheduleDTO mapToTeacherScheduleDTO(Schedule schedule) {

        TeacherScheduleDTO dto = new TeacherScheduleDTO();
        dto.setScheduleId(schedule.getId());
        dto.setCourseId(schedule.getCourseId());
        dto.setRoom(schedule.getRoom());
        dto.setStartTime(schedule.getStartTime());
        dto.setEndTime(schedule.getEndTime());

        return dto;
    }
    //enrichissement de course
    private void enrichWithCourseName(List<TeacherScheduleDTO> schedules) {
        for (TeacherScheduleDTO dto : schedules) {
            try {
                CourseDTO course = courseClient.getCourseById(dto.getCourseId());
                if (course != null) {
                    dto.setCourseName(course.getTitle());
                } else {
                    dto.setCourseName("Unknown Course");
                }
            } catch (Exception e) {
                dto.setCourseName("Unknown Course");
            }
        }
    }

    // ===================== READ =====================


    public TeacherScheduleDTO findById(Long id) {
        Schedule schedule = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        return mapToTeacherScheduleDTO(schedule);
    }

    public List<TeacherScheduleDTO> findByTeacherId(String teacherId) {

        List<TeacherScheduleDTO> schedules = repository.findByTeacherId(teacherId)
                .stream()
                .map(this::mapToTeacherScheduleDTO)
                .collect(Collectors.toList());

        enrichWithCourseName(schedules);

        return schedules;
    }


    public List<ScheduleResponse> findByCourseId(Long courseId) {
        return repository.findByCourseId(courseId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    public List<TeacherScheduleDTO> findAll() {

        List<TeacherScheduleDTO> schedules = repository.findAll()
                .stream()
                .map(this::mapToTeacherScheduleDTO)
                .collect(Collectors.toList());

        enrichWithCourseName(schedules);

        return schedules;
    }



    // ===================== UPDATE =====================
    public TeacherScheduleDTO update(Long id, ScheduleRequest request) {

        Schedule schedule = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

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

        return mapToTeacherScheduleDTO(repository.save(schedule));
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
