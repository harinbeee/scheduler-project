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
    private int pw;
    private LocalDateTime createdAt;


}
