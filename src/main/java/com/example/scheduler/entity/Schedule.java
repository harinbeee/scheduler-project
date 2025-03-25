package com.example.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private String todo;
    private String user;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // POST - 등록용 생성자
    public Schedule(String todo, String user,String password) {
        this.todo = todo;
        this.user = user;
        this.password = password;
    }

    // GET - 조회용 생성자
    public Schedule(Long id, String todo, String user, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.todo = todo;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
