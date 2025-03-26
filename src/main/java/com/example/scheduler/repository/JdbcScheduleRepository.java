package com.example.scheduler.repository;

import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class JdbcScheduleRepository implements ScheduleRepository{

    // ** 의존성
    private final JdbcTemplate jdbcTemplate;
    // ** 생성자 주입
    public JdbcScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        // 작성일, 수정일 - 현재로 설정
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        // DB에 id 저장
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        // 테이블에 데이터 저장하기
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo",schedule.getTodo());
        parameters.put("user", schedule.getUser());
        parameters.put("password", schedule.getPassword());
        parameters.put("created_at", Timestamp.valueOf(createdAt));
        parameters.put("updated_at", Timestamp.valueOf(updatedAt));

        // PK와 연결하기
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(
                key.longValue(),
                schedule.getTodo(),
                schedule.getUser(),
                createdAt,
                createdAt // updatedAt의 자리이지만 생성시 작성일==수정일 이므로
        );
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules(String user, String date) {

        StringBuilder sql = new StringBuilder("select * from schedule where 1=1 "); // sql 시작 부분 생성
        List<Object> fillteringSql = new ArrayList<>(); // 조건별로 사용할 sql문을 담는 list

        if (user != null){
            sql.append("AND user = ?");
            fillteringSql.add(user);
        }

        if (date != null) {
            sql.append("AND DATE(updated_at) = ?");
            fillteringSql.add(Date.valueOf(date));
        }

        sql.append("Order by updated_at desc"); // 수정일 순으로 내림차순 정렬

        return jdbcTemplate.query(sql.toString(),scheduleRowMapper(), fillteringSql.toArray());
    }

    @Override
    public Optional<Schedule> findScheduleById(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV2(),id);

        return result.stream().findAny();
    }


    @Override
    public int updateSchedule(Long id, String todo, String user, String password, LocalDateTime updatedAt) {
        updatedAt = LocalDateTime.now();
        return jdbcTemplate.update("UPDATE schedule SET todo = ?, user = ?, updated_at = ? WHERE id = ? AND password = ?",todo, user, updatedAt, id, password);
    }

    @Override
    public int deleteSchedule(Long id, String password) {
        return jdbcTemplate.update("delete from schedule where id = ? AND password =?", id, password);
    }

    // ** RowMapper
    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("user"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );
            }
        };
    }

    private RowMapper<Schedule> scheduleRowMapperV2() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("user"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );
            }
        };
    }


}
