package com.wmq.lecture.controller;

import com.wmq.lecture.service.BookLectureService;
import com.wmq.lecture.utils.ResultUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 实现讲座预约的控制接口
 */
@RestController
public class BookLectureController {
    @Resource
    BookLectureService bookLectureService;
    @RequestMapping("/admin/lectureBookers")
    public ResultUtil getlectureBookers(@Param("lecNumber")String lecNumber){
        return bookLectureService.getLectureBookers(lecNumber);
    }
    @GetMapping("/student/sign")
    public ResultUtil studentSign(String stuNumber,String lecNumber){
        System.out.println(stuNumber);
        System.out.println(lecNumber);
        return bookLectureService.checkSign(stuNumber,lecNumber);
    }

//    根据学生学号返回学生已经参与过的讲座信息
    @GetMapping("/student/participated")
    public ResultUtil participated(String stuNumber){
        System.out.println(stuNumber);
        return bookLectureService.participated(stuNumber);
    }
}
