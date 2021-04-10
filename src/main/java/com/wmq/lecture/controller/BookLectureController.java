package com.wmq.lecture.controller;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.wmq.lecture.entity.BookLecture;
import com.wmq.lecture.service.BookLectureService;
import com.wmq.lecture.service.ClassRoomService;
import com.wmq.lecture.utils.QRCodeUtil;
import com.wmq.lecture.utils.ResultUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 实现讲座预约的控制接口
 */

@RestController
@Validated
public class BookLectureController {
    @Resource
    BookLectureService bookLectureService;
    @Resource
    ClassRoomService classRoomService;
    @GetMapping("student/home")
    public ResultUtil getAll(){
        return bookLectureService.getAll();
    }
    @GetMapping("/pastLecture")
    public ResultUtil getPastLecture(@NotBlank(message = "日期不能为空") String date, @NotBlank(message="时间不能为空") String time){
        return bookLectureService.getPastLecture(date,time);
    }
    @GetMapping("student/getSeatInfo")
    public ResultUtil getSeatInfo(@NotBlank(message = "宣讲室名称不能为空") String roomName,@NotBlank(message = "讲座编号不能为空") String lecNumber){
        System.out.println("roomName:"+roomName);
        System.out.println("lecNumber:"+lecNumber);
        return bookLectureService.getSeatInfo(roomName,lecNumber);
    }
    /**
     * 管理员查看指定讲座有哪些学生报名订座
     * */
    @RequestMapping("/admin/lectureBookers")
    public ResultUtil getLectureBookers(@NotBlank(message = "lecNUmber不能为空") String lecNumber){
        return bookLectureService.getLectureBookers(lecNumber);
    }

    /**
     * 学生才有签到的权限
     * */
    @GetMapping("/student/sign")
    public ResultUtil studentSign(@NotBlank(message = "学号不能为空") String stuNumber,@NotBlank(message = "讲座编号不能为空") String lecNumber){
        System.out.println(stuNumber);
        System.out.println(lecNumber);
        return bookLectureService.checkSign(stuNumber,lecNumber);
    }
    /**
     * 根据学生学号返回学生已经参与过的讲座信息
     * */
    @GetMapping("/student/participated")
    public ResultUtil participated(@NotBlank(message = "学号不能为空") String stuNumber){
        System.out.println(stuNumber);
        return bookLectureService.participated(stuNumber);
    }
    /**
     * 参加过的讲座可以写评价
     * */
    @RequestMapping("student/doComment")
    public ResultUtil doComment(@RequestBody @Validated BookLecture bookLecture){
        System.out.println(bookLecture.toString());
        return bookLectureService.doComments(bookLecture);
    }

    @PostMapping("student/buySeat")
    public ResultUtil buySeat(@RequestBody @Validated  BookLecture bookLecture){
        ResultUtil resultUtil = new ResultUtil();
        System.out.println(bookLecture.toString());
        String seatNumber = "seat_"+bookLecture.getCommented();
        String roomName = bookLecture.getRoomNumber();
        bookLecture.setCommented(0);
        ResultUtil result1 = bookLectureService.bookLecture(bookLecture);
        ResultUtil result2 = classRoomService.buySeat(seatNumber,roomName);
        if(result1.getCode()==200&&result2.getCode()==200){
            result1.setCode(200);
            result1.setSetMessage("预约成功");
            return result1;
        }
        result1.setSetMessage("预约失败");
        result1.setCode(201);
        return result1;
    }

    /**
     *只有管理员才能打开讲座待签到的二维码
     * @param lecName
     * */
    @GetMapping(value = "/admin/qrCode")
    public void getCode(@NotBlank(message = "讲座标题不能为空") String lecName ,@NotBlank(message="讲座编号不能为空") String lecNumber, HttpServletResponse response) throws IOException {
        // 设置响应流信息
        response.setContentType("image/jpg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        OutputStream stream = response.getOutputStream();
        System.out.println("查看传到后端的讲座编号");
        System.out.println(lecName);
        System.out.println(lecNumber);
//        扫描二维码的界面在未登录情况下可以进入
        String content = " http://127.0.0.1:8080/signUp?lecName="+lecName+"?lecNumber="+lecNumber;
        //根据url获取一个二维码图片
        BitMatrix bitMatrix = QRCodeUtil.createCode(content);
        //以流的形式输出到前端
        MatrixToImageWriter.writeToStream(bitMatrix , "jpg" , stream);
    }

}
