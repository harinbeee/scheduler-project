package com.example.scheduler.controller;

import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    // **Service 주입하기
    private final ScheduleService scheduleService;
    // **생성자
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {

        return new ResponseEntity<>(scheduleService.saveSchedule(dto), HttpStatus.CREATED);

    }

    @GetMapping
    public List<ScheduleResponseDto> findAllSchedules() {

        return scheduleService.findAllSchedules();

    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById (@PathVariable Long id) {

        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule (
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto dto
    ) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule (
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto dto
    ){
        scheduleService.deleteSchedule(id,dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

