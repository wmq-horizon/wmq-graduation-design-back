package com.wmq.lecture.controller;

import com.wmq.lecture.entity.BookLecture;
import com.wmq.lecture.service.BookLectureService;
import com.wmq.lecture.service.ClassRoomService;
import com.wmq.lecture.utils.ResultUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 实现讲座预约的控制接口
 */

@RestController
public class BookLectureController {
    @Resource
    BookLectureService bookLectureService;
    @Resource
    ClassRoomService classRoomService;

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

//    参加过的讲座可以写评价
    @RequestMapping("student/doComment")
    public ResultUtil doComment(@RequestBody BookLecture bookLecture){
        System.out.println(bookLecture.toString());
        bookLectureService.doComments(bookLecture);
        return null;
    }

    @PostMapping("student/buySeat")
    public ResultUtil buySeat(@RequestBody BookLecture bookLecture){
        System.out.println(bookLecture.toString());
        String seatNumber = "seat_"+bookLecture.getCommented();
        String roomName = bookLecture.getRoomNumber();
        bookLecture.setCommented(0);
        classRoomService.buySeat(seatNumber,roomName);
        bookLectureService.bookLecture(bookLecture);
        return null;
    }

}
