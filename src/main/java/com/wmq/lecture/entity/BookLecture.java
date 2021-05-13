package com.wmq.lecture.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author horizon
 */
@Data
public class BookLecture {
    @NotBlank(message = "学号不能为空！")
    @NotNull(message = "学号不能为NULL！")
    @Length(max = 10,min=10,message="学号长度不对")
    private String stuNumber;
    private String roomNumber;
    @NotBlank(message = "讲座编号不能为空！")
    @NotNull(message = "讲座编号不能为NULL！")
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
    private String speaker;
}