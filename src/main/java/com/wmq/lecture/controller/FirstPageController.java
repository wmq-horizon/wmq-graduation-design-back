package com.wmq.lecture.controller;

import com.wmq.lecture.service.FirstPageService;
import com.wmq.lecture.utils.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lenovo
 */
@RestController
@RequestMapping("/student")
public class FirstPageController {
    @Resource
    FirstPageService firstPageService;
    @GetMapping("home")
    public ResultUtil getAll(){
        return firstPageService.getAll();
    }
    @GetMapping("getSeatInfo")
    public ResultUtil getSeatInfo(@RequestParam(value="roomName")String roomName,@RequestParam(value="lecNumber")String lecNumber){
        System.out.println("roomName:"+roomName);
        System.out.println("lecNumber:"+lecNumber);
        return firstPageService.getSeatInfo(roomName,lecNumber);
    }
}
