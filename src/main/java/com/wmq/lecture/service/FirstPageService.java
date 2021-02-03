package com.wmq.lecture.service;

import com.wmq.lecture.mapper.ClassRoomMapper;
import com.wmq.lecture.mapper.LectureMapper;
import com.wmq.lecture.utils.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author lenovo
 * 处理mapper层和entity的数据，服务层编写的服务是可以进行复用的
 */
@Service
public class FirstPageService {
    @Resource
    LectureMapper lectureMapper;
    public ResultUtil getAll(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setData(lectureMapper.selectAll());
        if(resultUtil.getData()!=null){
            resultUtil.setCode(200);
            resultUtil.setSetMessage("查询所有的讲座信息");
            return  resultUtil;
        }else{
            resultUtil.setCode(201);
            resultUtil.setSetMessage("查询信息失败");
            return  resultUtil;
        }

    }
    @Resource
    ClassRoomMapper classRoomMapper ;
    public ResultUtil getSeatInfo(String roomName){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setData(classRoomMapper.getSeatInfo(roomName));
        if(resultUtil.getData()!=null){
            resultUtil.setCode(200);
            resultUtil.setSetMessage("查询所有的讲座信息");
            return  resultUtil;
        }else{
            resultUtil.setCode(201);
            resultUtil.setSetMessage("查询信息失败");
            return  resultUtil;
        }
    }

}
