package com.wmq.lecture.service;

import com.wmq.lecture.mapper.BookLectureMapper;
import com.wmq.lecture.utils.ResultUtil;
import org.apache.poi.hssf.dev.ReSave;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lenovo
 */
@Service
public class BookLectureService {
    @Resource
    BookLectureMapper bookLectureMapper;

    public ResultUtil getLectureBookers(String lecNumber){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setData(bookLectureMapper.getLectureBookers(lecNumber));
        resultUtil.setSetMessage("查看预定蒋总信息的人");
        resultUtil.setCode(200);
        return resultUtil;
    }

    public ResultUtil checkSign(String stuNumber,String lecNumber){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(200);
        resultUtil.setSetMessage("学生签到检查");
        System.out.println("学生签到检查");
        resultUtil.setData(bookLectureMapper.checkSign(stuNumber,lecNumber));
        return resultUtil;
    }
}
