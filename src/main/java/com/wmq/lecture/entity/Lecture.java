package com.wmq.lecture.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

/**
 * @author lenovo
 */
@Data
public class Lecture {
    private String lecNumber;
    private String lecRoom;
    private String title;
    private String speaker;
    private Double lecScore;
    private String lecDate;
    private String lecTime;
    private String introduction;
    private String content;
}