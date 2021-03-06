package com.wmq.lecture.controller;

import com.wmq.lecture.entity.Lecture;
import com.wmq.lecture.service.LectureService;
import com.wmq.lecture.utils.ResultUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lenovo
 */
@RestController

public class LectureController {

    @Resource
    LectureService lectureService;
    @GetMapping("student/lectureInfo")
    public ResultUtil getInitTableInfo(){
            return lectureService.getInitTableInfo();
    }

    @PostMapping("admin/createNewLecture")
    public ResultUtil createNewLecture(@RequestBody Lecture lecture){
        return lectureService.createNewLecture(lecture);
    }
    @RequestMapping("student/getTopLecture")
    public ResultUtil getTopLecture(){
         return lectureService.getTopLectureInfo();
    };
    @RequestMapping("student/getTopStudent")
    public ResultUtil getTopStudent(){
        return lectureService.getTopStudent();
    }
    @RequestMapping("student/getTopThreeSpeaker")
    public ResultUtil getTopSpeaker(){
        return lectureService.getTopSpeaker();
    }
    @RequestMapping("/admin/deleteLecture")
    public ResultUtil deleteLecture(@Param("lecNumber")String lecNumber){
        return lectureService.deleteLecture(lecNumber);
    }
    @RequestMapping("admin/updateLecture")
    @ResponseBody
    public ResultUtil updateLecture(@RequestBody Lecture lecture){
        System.out.println("前台时间："+lecture.getLecDate());
//        SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd");
//        System.out.println("格式化之后的时间："+f.format(lecture.getLecDate()));
//        lecture.setLecDate(f.format(lecture.getLecDate()));
//        System.out.println("格式化之后的日期吼吼："+lecture.getLecDate());
//        System.out.println("更新讲座信息");
        System.out.println(lecture);
        return lectureService.updateLecture(lecture);
    }
}
