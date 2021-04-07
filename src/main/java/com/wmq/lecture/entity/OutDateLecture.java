package com.wmq.lecture.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author horizon
 */
@Data
public class OutDateLecture {
    private String lecNumber;
    private String lecRoom;
    private String title;
    private String speaker;
    private Double lecScore;
    private Date lecDate;
    private String lecTime;
    private String introduction;
    private String content;
}