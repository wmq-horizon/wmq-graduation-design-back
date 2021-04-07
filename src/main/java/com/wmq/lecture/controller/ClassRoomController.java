package com.wmq.lecture.controller;

import com.wmq.lecture.entity.ClassRoom;
import com.wmq.lecture.entity.Lecture;
import com.wmq.lecture.entity.LectureRoom;
import com.wmq.lecture.service.ClassRoomService;
import com.wmq.lecture.service.LectureService;
import com.wmq.lecture.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lenovo
 */
@RestController
public class ClassRoomController {
    @Resource
    ClassRoomService classRoomService;
    @GetMapping("admin/getClassRoomInfo")
    public ResultUtil getClassRoomInfo(){
        return classRoomService.getClassRoomInfo();
    }
    @PostMapping("admin/postClassRoomInfo")
    public ResultUtil postClassRoomInfo(ClassRoom classRoom){
        return classRoomService.postClassRoomInfo(classRoom);
    }

    @PostMapping("admin/newRoom")
    public ResultUtil newRoom(@RequestBody ClassRoom room){
        return classRoomService.newRoom(room);
    }

    @GetMapping("/student/topRoom")
    public ResultUtil topRoom(){
        return classRoomService.getTopRoom();
    }
}
