package com.wmq.lecture.controller;

import com.wmq.lecture.entity.Lecture;
import com.wmq.lecture.service.LectureService;
import com.wmq.lecture.utils.ResultUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lenovo
 */
@RestController

public class LectureController {

    @Resource
    LectureService lectureService;

    @GetMapping("admin/getLectureOfToday")
    public ResultUtil getTodayLecture(@NotBlank(message="日期不能为空") String lecDate){
        System.out.println(lecDate);
        System.out.println("查询今日的讲座信息");
        return lectureService.getTodayLecture(lecDate);
    }
    @GetMapping("student/lectureInfo")
    public ResultUtil getInitTableInfo(){
            return lectureService.getInitTableInfo();
    }

    @PostMapping("admin/createNewLecture")
    public ResultUtil createNewLecture(@RequestBody @Validated  Lecture lecture){
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
    public ResultUtil deleteLecture(@NotBlank(message="讲座编号不能为空") String lecNumber){
        return lectureService.deleteLecture(lecNumber);
    }
    @RequestMapping("admin/updateLecture")
    @ResponseBody
    public ResultUtil updateLecture(@RequestBody @Validated Lecture lecture){
        System.out.println("前台时间："+lecture.getLecDate());
        System.out.println(lecture);
        return lectureService.updateLecture(lecture);
    }
}
