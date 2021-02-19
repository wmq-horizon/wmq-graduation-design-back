package com.wmq.lecture.service;

import com.wmq.lecture.mapper.ClassRoomMapper;
import com.wmq.lecture.mapper.LectureMapper;
import com.wmq.lecture.mapper.LectureRoomMapper;
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
    LectureRoomMapper lectureRoomMapper;
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
    /**
    * 返回指定讲座编号和宣讲室名称的座位信息
    * */
    public ResultUtil getSeatInfo(String roomName,String lecNumber){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setData(lectureRoomMapper.getSeatInfo(roomName,lecNumber));
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
