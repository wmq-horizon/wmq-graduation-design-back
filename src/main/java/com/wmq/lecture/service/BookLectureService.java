package com.wmq.lecture.service;

import com.wmq.lecture.entity.BookLecture;
import com.wmq.lecture.mapper.BookLectureMapper;
import com.wmq.lecture.utils.ResultUtil;
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
    public ResultUtil participated(String stuNumber){
        System.out.println("stuNumber:"+stuNumber);
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(200);
        resultUtil.setSetMessage("根据学号返回学生参与过的讲座信息");
        resultUtil.setData(bookLectureMapper.selectParticipatedLecture(stuNumber));
        return resultUtil;
    }
    public ResultUtil doComments(BookLecture bookLecture){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setData(bookLectureMapper.doComment(bookLecture));
        resultUtil.setSetMessage("添加啊评论成功");
        resultUtil.setCode(200);
        return resultUtil;
    }

    /**
     * 登录的用户需要预定座位的时候则填写bookLecture信息表
     * */
    public ResultUtil bookLecture(BookLecture bookLecture){
        ResultUtil resultUtil = new ResultUtil();
        bookLectureMapper.insert(bookLecture);
        return resultUtil;
    }
}
