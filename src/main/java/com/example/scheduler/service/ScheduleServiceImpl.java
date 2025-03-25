package com.example.scheduler.service;

import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    //** repository 주입하기
    private final ScheduleRepository scheduleRepository;
    //** 생성자
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {

        // 자동 생성 - 작성일
        LocalDateTime createdAt = LocalDateTime.now();

        // Schedule 객체 생성 ID는 repository에서 !
        Schedule schedule = new Schedule(dto.getTodo(), dto.getUser(), createdAt);

        // Schedule DB에 저장
        Schedule savedSchedule = scheduleRepository.saveSchedule(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }
}
