package com.wmq.lecture.service;

import com.wmq.lecture.entity.Lecture;
import com.wmq.lecture.mapper.LectureMapper;
import com.wmq.lecture.utils.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lenovo
 */
@Service
public class LectureService {
    @Resource
    LectureMapper lectureMapper;
    public ResultUtil getInitTableInfo(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setSetMessage("返回讲座信息");
        resultUtil.setCode(200);
        resultUtil.setData(lectureMapper.selectAll());
        return resultUtil;
    }
    public ResultUtil createNewLecture(Lecture lecture){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setSetMessage("创建讲座成功");
        resultUtil.setCode(200);
        lectureMapper.insert(lecture);
        return resultUtil;
    }
}
