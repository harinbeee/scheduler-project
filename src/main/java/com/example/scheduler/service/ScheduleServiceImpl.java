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
import java.util.Optional;

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

        // Schedule 객체 생성
        Schedule schedule = new Schedule(dto.getTodo(),dto.getUser(),dto.getPassword());

        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {

        List<ScheduleResponseDto> allSchedules = scheduleRepository.findAllSchedules();

        return allSchedules;
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {

        Optional<Schedule> optionalSchedule = scheduleRepository.findScheduleById(id);

        // NPE 방지
        if (optionalSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return new ScheduleResponseDto(optionalSchedule.get());
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto ) {

        if (dto.getTodo() == null || dto.getUser() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "할일과 작성자명을 모두 입력해주세요");
        }

        int updatedRow = scheduleRepository.updateSchedule(id, dto.getTodo(), dto.getUser(), dto.getPassword(), LocalDateTime.now());

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }

        return new ScheduleResponseDto(scheduleRepository.findScheduleById(id).get());
    }

    @Override
    public void deleteSchedule(Long id, ScheduleRequestDto dto) {

        int deleteRow = scheduleRepository.deleteSchedule(id, dto.getPassword());


    }
}
