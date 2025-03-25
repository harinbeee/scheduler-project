package com.example.scheduler.repository;

import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule (Schedule schedule);

    List<ScheduleResponseDto> findAllSchedules(String user, String date);

    Optional<Schedule> findScheduleById (Long id);

    int updateSchedule(Long id, String todo, String user, String password, LocalDateTime updatedAt);

    int deleteSchedule(Long id, String password);
}
