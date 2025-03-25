package com.example.scheduler.controller;

import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    // **Service 주입하기
    private final ScheduleService scheduleService;
    // **생성자
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    // 일정 등록
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {

        return new ResponseEntity<>(scheduleService.saveSchedule(dto), HttpStatus.CREATED);

    }

//    @GetMapping
//    public List<ScheduleResponseDto> findAllSchedules() {
//
//        List<ScheduleResponseDto> responseList = new ArrayList<>();
//
//        for (Schedule schedule : scheduleList.values()) {
//            ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
//            responseList.add(responseDto);
//        }
//
//        return responseList;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ScheduleResponseDto> findScheduleById (@PathVariable Long id) {
//
//        Schedule schedule = scheduleList.get(id);
//
//        if (schedule == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
//
//    }
}

