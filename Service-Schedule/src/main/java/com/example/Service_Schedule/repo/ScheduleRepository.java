package com.example.Service_Schedule.repo;

import com.example.Service_Schedule.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // Trouver les schedules par cours
    List<Schedule> findByCourseId(Long courseId);

    // Trouver les schedules par enseignant
    List<Schedule> findByTeacherId(String teacherId);

    // Trouver les schedules par salle
    List<Schedule> findByRoom(String room);

    // Trouver les schedules par jour
    List<Schedule> findByDay(DayOfWeek day);

    // Vérifier si une salle est occupée à un créneau donné
    @Query("SELECT COUNT(s) > 0 FROM Schedule s WHERE s.room = :room AND s.day = :day " +
            "AND ((s.startTime <= :endTime AND s.endTime >= :startTime))")
    boolean isRoomOccupied(@Param("room") String room,
                           @Param("day") DayOfWeek day,
                           @Param("startTime") String startTime,
                           @Param("endTime") String endTime);

    // Trouver les schedules d'un enseignant à un jour donné
    List<Schedule> findByTeacherIdAndDay(String teacherId, DayOfWeek day);

    List<Schedule> findByRoomAndDay(String room, DayOfWeek day);

}