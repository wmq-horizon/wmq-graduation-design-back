package com.wmq.lecture.service;

import com.wmq.lecture.entity.ClassRoom;
import com.wmq.lecture.mapper.ClassRoomMapper;
import com.wmq.lecture.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lenovo
 */

@Service
public class ClassRoomService {
   @Resource
    ClassRoomMapper classRoomMapper;

    public ResultUtil getClassRoomInfo(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(200);
        resultUtil.setSetMessage("返回座位的信息");
        resultUtil.setData(classRoomMapper.selectAll());
        return resultUtil;
    }

    public ResultUtil postClassRoomInfo(ClassRoom classRoom) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(200);
        resultUtil.setSetMessage("上传座位信息");
        classRoomMapper.insert(classRoom);
        return resultUtil;
    }

}
