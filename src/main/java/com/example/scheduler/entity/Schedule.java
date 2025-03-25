package com.example.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    @Setter
    private Long id;
    private String todo;
    private String user;
    private int pw;
    private LocalDateTime createdAt;

    public Schedule(String todo, String user, LocalDateTime createdAt) {
        this.todo = todo;
        this.user = user;
        this.createdAt = createdAt;
    }


}
