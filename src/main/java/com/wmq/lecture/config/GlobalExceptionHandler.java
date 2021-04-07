package com.wmq.lecture.config;

import com.wmq.lecture.utils.ResultUtil;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @author horizon
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 方法参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultUtil handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(404);
        resultUtil.setSetMessage(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        return resultUtil;
    }
}
