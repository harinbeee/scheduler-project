package com.example.scheduler.repository;

import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    @Override
    public Schedule saveSchedule(Schedule schedule) {

        // 자동 생성 - 식별자
        Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet())+ 1;
        schedule.setId(scheduleId);

        scheduleList.put(scheduleId, schedule);

        return schedule;
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        // 리스트 초기화
        List<ScheduleResponseDto> allSchedules = new ArrayList<>();
        // hashmap -> list
        for (Schedule schedule: scheduleList.values()) {
            ScheduleResponseDto dto = new ScheduleResponseDto(schedule);
            allSchedules.add(dto);
        }
        return allSchedules;
    }

    @Override
    public Schedule findScheduleById(Long id) {

        return scheduleList.get(id);

    }
}
