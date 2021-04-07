package com.wmq.lecture.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author horizon
 */
@Data
public class Users {
    @NotBlank(message = "用户账号不能为空！")
    @NotNull(message = "用户账号不能为空！")
    private String uid;
    private String name;
    private String password;
    private Integer integrity;
    private String role;
    private Double score;
    private Integer status;
}