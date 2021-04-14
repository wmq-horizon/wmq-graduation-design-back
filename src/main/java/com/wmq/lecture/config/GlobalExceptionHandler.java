package com.wmq.lecture.config;

import com.wmq.lecture.utils.ResultUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.transaction.TransactionRequiredException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

/**
 * @author horizon
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 方法参数校验
     * 捕获@RequestBody参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultUtil handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(404);
        resultUtil.setSetMessage(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        return resultUtil;
    }
    /**
     * 拦截捕捉自定义异常 ConstraintViolationException.class
     * @param ex
     * 捕获@RequestParam参数异常
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResultUtil constraintViolationExceptionHandler(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            msgList.add(cvl.getMessageTemplate());
        }
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(404);
        resultUtil.setSetMessage(msgList.toString());
        return resultUtil;
    }
    /**
     * sql异常
     * @param exception
     * @return
     */
    @ExceptionHandler({BadSqlGrammarException.class, SQLException.class})
    public ResultUtil sqlException(Exception exception) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(404);
        System.out.println(exception.getMessage());
        resultUtil.setSetMessage("请求异常");
        return resultUtil;
    }

    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ResultUtil sQLIntegrityConstraintViolationException(Exception exception) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(404);
        System.out.println(exception.getMessage());
        resultUtil.setSetMessage("失败");
        return resultUtil;
    }

    @ExceptionHandler({DuplicateKeyException.class})
    public ResultUtil duplicateKeyException(DuplicateKeyException exception) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(404);
        System.out.println(exception.getMessage());
        resultUtil.setSetMessage("重复插入");
        return resultUtil;
    }


    @ExceptionHandler({TransactionRequiredException.class})
    public ResultUtil transactionRequiredException(TransactionRequiredException exception) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(404);
        System.out.println(exception.getMessage());
        resultUtil.setSetMessage("请求异常");
        return resultUtil;
    }
}
