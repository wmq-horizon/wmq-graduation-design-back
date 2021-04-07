package com.wmq.lecture.controller;

import com.wmq.lecture.entity.Room;
import com.wmq.lecture.service.ClassRoomService;
import com.wmq.lecture.utils.ResultUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lenovo
 */
@RestController
public class RoomController {
    @Resource
    ClassRoomService classRoomService;
    @GetMapping("admin/getClassRoomInfo")
    public ResultUtil getClassRoomInfo(){
        return classRoomService.getClassRoomInfo();
    }
    @PostMapping("admin/postClassRoomInfo")
    public ResultUtil postClassRoomInfo(@Validated  Room room){
        return classRoomService.postClassRoomInfo(room);
    }

    @PostMapping("admin/newRoom")
    public ResultUtil newRoom(@RequestBody @Validated Room room){
        return classRoomService.newRoom(room);
    }

    @GetMapping("/student/topRoom")
    public ResultUtil topRoom(){
        return classRoomService.getTopRoom();
    }
}
