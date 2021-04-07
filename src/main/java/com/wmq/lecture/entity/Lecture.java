package com.wmq.lecture.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;

/**
 * @author horizon
 */
@Data
public class Lecture {
    @NotBlank(message = "讲座编号不能为空！")
    @NotNull(message = "讲座编号不能为NULL！")
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