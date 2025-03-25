package com.example.scheduler.controller;

import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    // 일정 전체 리스트
    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    // 일정 등록
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {

        // id(식별자)가 1씩 증가
        Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet())+1;

        // 일정 생성
        LocalDateTime createdAt = LocalDateTime.now();

        // 일정 객체 생성
        Schedule schedule = new Schedule(scheduleId, dto.getTodo(), dto.getUser(), dto.getPw(), createdAt);

        //
        scheduleList.put(scheduleId, schedule);

        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.CREATED);

    }

    @GetMapping
    public List<ScheduleResponseDto> findAllSchedules() {

        List<ScheduleResponseDto> responseList = new ArrayList<>();

        for (Schedule schedule : scheduleList.values()) {
            ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
            responseList.add(responseDto);
        }

        return responseList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById (@PathVariable Long id) {

        Schedule schedule = scheduleList.get(id);

        if (schedule == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);

    }
}

