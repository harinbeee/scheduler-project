package com.example.scheduler.repository;

import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;

import java.util.List;
import java.util.Optional;


public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule (Schedule schedule);

    List<ScheduleResponseDto> findAllSchedules();

    Optional<Schedule> findScheduleById (Long id);

}
