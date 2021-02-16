package com.wmq.lecture.controller;

import com.wmq.lecture.entity.Lecture;
import com.wmq.lecture.service.LectureService;
import com.wmq.lecture.utils.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
}
