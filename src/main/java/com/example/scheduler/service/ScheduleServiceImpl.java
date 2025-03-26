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

    // **의존성
    private final ScheduleRepository scheduleRepository;
    // **생성자 주입
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {

        Schedule schedule = new Schedule(dto.getTodo(),dto.getUser(),dto.getPassword());

        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules(String user, String date) {

        // List로 전체 일정 담기
        List<ScheduleResponseDto> allSchedules = scheduleRepository.findAllSchedules(user, date);

        return allSchedules;
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {

        Optional<Schedule> optionalSchedule = scheduleRepository.findScheduleById(id);

        // NPE 방지
        if (optionalSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 id = " + id);
        }

        return new ScheduleResponseDto(optionalSchedule.get());
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto ) {

        // null 값 방지
        if (dto.getTodo() == null || dto.getUser() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정할 일정과 작성자명을 모두 입력해주세요");
        }

        int updatedRow = scheduleRepository.updateSchedule(id, dto.getTodo(), dto.getUser(), dto.getPassword(), LocalDateTime.now());

        // NPE 방지
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "수정할 일정이 없거나 비밀번호가 일치하지 않습니다.");
        }

        // 선택 일정 수정 후 수정된 일정 id로 찾아 반환
        return new ScheduleResponseDto(scheduleRepository.findScheduleById(id).get());
    }

    @Override
    public void deleteSchedule(Long id, ScheduleRequestDto dto) {

        int deleteRow = scheduleRepository.deleteSchedule(id, dto.getPassword());

        // null 값 방지
        if (deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정이 존재하지 않거나 비밀번호가 일치하지 않습니다.");

        }
    }
}
