package com.wmq.lecture.controller;

import com.wmq.lecture.entity.ClassRoom;
import com.wmq.lecture.service.ClassRoomService;
import com.wmq.lecture.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("buySeat")
    public ResultUtil buySeat(@RequestParam(value="seatNumberAndName")String seatNumberAndName){
        String[] strs = new String[2];
        int index = 0;
        for(String str:seatNumberAndName.split("=")){
            strs[index] = str;
            index++;
        }
        ResultUtil resultUtil = new ResultUtil();
        String seatNumber = strs[0];
        String room_name = "'"+strs[1]+"'";
        System.out.println(seatNumber);
        System.out.println(room_name);
        classRoomService.buySeatSercice(seatNumber,room_name);
        return resultUtil;
    }
    @GetMapping("classRoomInfo")
    public ResultUtil getInitData(){
        return classRoomService.getClassRoomInfo();
    }
    @PostMapping("/newRoom")
    public ResultUtil newRoom(@RequestBody ClassRoom room){
        return classRoomService.newRoom(room);
    }
}
