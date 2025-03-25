package com.example.scheduler.service;

import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto findScheduleById (Long id);

    ScheduleResponseDto updateSchedule (Long id, ScheduleRequestDto dto);

    void deleteSchedule (Long id, ScheduleRequestDto dto);
}
