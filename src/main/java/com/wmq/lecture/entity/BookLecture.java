package com.wmq.lecture.entity;

import lombok.Data;

@Data
public class BookLecture {
    private String stuNumber;
    private String roomNumber;
    private String lecNumber;
    private Integer rrow;
    private Integer collumn;
    private String time;
    private String date;
    private Integer arrived;
    private Double score;
    private Integer integrity;
    private String comments;
    private Integer commented;
    private String title;
}