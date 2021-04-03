package com.wmq.lecture.controller;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.wmq.lecture.utils.QRCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
@RestController
public class QrCodeController {
    /**
     *
     * @param lecName
     * */
    @GetMapping(value = "/activity/create/activity-code")
    public void getCode(String lecName ,String lecNumber, HttpServletResponse response) throws IOException {
        // 设置响应流信息
        response.setContentType("image/jpg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        OutputStream stream = response.getOutputStream();
        //type是1，生成活动详情、报名的二维码，type是2，生成活动签到的二维码
        System.out.println("查看传到后端的讲座编号");
        System.out.println(lecName);
        System.out.println(lecNumber);
        String content = " http://127.0.0.1:8080/signPage?lecName="+lecName+"?lecNumber="+lecNumber;
        //根据url获取一个二维码图片
        BitMatrix bitMatrix = QRCodeUtil.createCode(content);
        //以流的形式输出到前端
        MatrixToImageWriter.writeToStream(bitMatrix , "jpg" , stream);
    }
}
