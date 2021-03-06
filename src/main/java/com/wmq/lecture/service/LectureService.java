package com.wmq.lecture.service;

import com.wmq.lecture.entity.BookLecture;
import com.wmq.lecture.entity.ClassRoom;
import com.wmq.lecture.entity.Lecture;
import com.wmq.lecture.entity.LectureRoom;
import com.wmq.lecture.mapper.BookLectureMapper;
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
    @Resource
    BookLectureMapper bookLectureMapper;

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

    public ResultUtil getTopLectureInfo(){
        ResultUtil resultUtil = new ResultUtil();

        resultUtil.setSetMessage("获取前讲座排名10的数据");
        resultUtil.setData(bookLectureMapper.topLecture());
        resultUtil.setCode(200);
        return resultUtil;
    }

    public ResultUtil getTopStudent(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setSetMessage("获取预约讲座次数最多的10人");
        resultUtil.setCode(200);
        resultUtil.setData(bookLectureMapper.topStudent());
        return resultUtil;
    }
    public ResultUtil getTopSpeaker(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(200);
        resultUtil.setSetMessage("获取最受欢迎的前10名讲师");
        resultUtil.setData(bookLectureMapper.topSpeaker());
        return resultUtil;
    }
    public ResultUtil deleteLecture(String lecNumber){
        ResultUtil resultUtil = new ResultUtil();
        if(lecNumber==null){
            resultUtil.setSetMessage("待删除的讲座不存在");
            resultUtil.setCode(401);
        }else{
            lectureMapper.deleteLecture(lecNumber);
            resultUtil.setSetMessage("成功删除讲座");
            resultUtil.setCode(200);
        }
        return resultUtil;
    }
    public ResultUtil updateLecture(Lecture lecture){
        ResultUtil resultUtil = new ResultUtil();
        if (lecture==null || lecture.getLecNumber()==null){
            resultUtil.setSetMessage("待更新的讲座为空");
            resultUtil.setCode(401);
        }else{
            int status = lectureMapper.updateLecture(lecture);
            if(status==0){
                resultUtil.setSetMessage("更新失败");
                resultUtil.setCode(201);
            }else{
                resultUtil.setSetMessage("更新成功");
                resultUtil.setCode(200);
            }
            System.out.println(status);
        }
        return resultUtil;
    }

}
