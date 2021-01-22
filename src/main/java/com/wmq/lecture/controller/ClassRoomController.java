package com.wmq.lecture.controller;

import com.wmq.lecture.entity.ClassRoom;
import com.wmq.lecture.service.ClassRoomService;
import com.wmq.lecture.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author lenovo
 */
@RestController
@RequestMapping("/classRoomInfo")
public class ClassRoomController {
    @Resource
    ClassRoomService classRoomService;
    @GetMapping("getClassRoomInfo")
    public ResultUtil getClassRoomInfo(){
        return classRoomService.getClassRoomInfo();
    }
    @PostMapping("postClassRoomInfo")
    public ResultUtil postClassRoomInfo(ClassRoom classRoom){
        return classRoomService.postClassRoomInfo(classRoom);
    }

}
