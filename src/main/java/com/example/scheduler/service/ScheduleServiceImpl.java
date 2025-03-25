package com.example.scheduler.service;

import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {

        List<ScheduleResponseDto> allSchedules = scheduleRepository.findAllSchedules();

        return allSchedules;
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {

        Schedule schedule = scheduleRepository.findScheduleById(id);

        // NPE 방지
        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return new ScheduleResponseDto(schedule);
    }
}
