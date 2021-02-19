package com.wmq.lecture.service;

import com.wmq.lecture.entity.ClassRoom;
import com.wmq.lecture.entity.Lecture;
import com.wmq.lecture.entity.LectureRoom;
import com.wmq.lecture.mapper.ClassRoomMapper;
import com.wmq.lecture.mapper.LectureMapper;
import com.wmq.lecture.mapper.LectureRoomMapper;
import com.wmq.lecture.utils.ResultUtil;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lenovo
 */
@Service
public class LectureService {
    @Resource
    LectureMapper lectureMapper;
    @Resource
    LectureRoomMapper lectureRoomMapper;
    @Resource
    ClassRoomMapper classRoomMapper;
    public ResultUtil getInitTableInfo(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setSetMessage("返回讲座信息");
        resultUtil.setCode(200);
        resultUtil.setData(lectureMapper.selectAll());
        return resultUtil;
    }
    /**
     *
     * 创建讲座的同时添加一条宣讲室讲座占用表
     */
    public ResultUtil createNewLecture(Lecture lecture){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setSetMessage("创建讲座成功");
        resultUtil.setCode(200);
        lectureMapper.insert(lecture);

        //根据讲座选定的宣讲室名称查询该宣讲室拥有的行数和列数
        ClassRoom classRoom = classRoomMapper.selectRoomByRoomName(lecture.getLecRoom());
        int rrow = classRoom.getRowCount();
        int collumn = classRoom.getColCount();

        //设置接下来即将在数据库中插入的数据的参数
        LectureRoom lectureRoom = new LectureRoom();
        lectureRoom.setLecNumber(lecture.getLecNumber());
        System.out.println(lecture.getLecNumber());
        lectureRoom.setRoomNumber(lecture.getLecRoom());
        System.out.println(lecture.getLecRoom());
        lectureRoom.setColCount(collumn);
        System.out.println(collumn);
        lectureRoom.setRowCount(rrow);
        System.out.println(rrow);
        lectureRoomMapper.insert(lectureRoom);
        return resultUtil;
    }


}
