package com.wmq.lecture.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author horizon
 */
@Data
public class Users {
    @NotBlank(message = "用户账号不能为空！")
    @NotNull(message = "用户账号不能为NULL！")
    @Length(max = 10,min=10,message="账号长度为10")
    private String uid;
    private String name;
    @NotBlank(message = "密码不能为空！")
    @NotNull(message = "密码不能为NULL！")
    private String password;
    private Integer integrity;
    private String role;
    private Double score;
    private Integer status;
}