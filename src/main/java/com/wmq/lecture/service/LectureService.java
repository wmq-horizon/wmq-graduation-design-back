package com.wmq.lecture.service;

import com.wmq.lecture.entity.Room;
import com.wmq.lecture.entity.Lecture;
import com.wmq.lecture.entity.LectureRoom;
import com.wmq.lecture.mapper.BookLectureMapper;
import com.wmq.lecture.mapper.RoomMapper;
import com.wmq.lecture.mapper.LectureMapper;
import com.wmq.lecture.mapper.LectureRoomMapper;
import com.wmq.lecture.utils.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author horizon
 */
@Service
public class LectureService {
    @Resource
    LectureMapper lectureMapper;
    @Resource
    LectureRoomMapper lectureRoomMapper;
    @Resource
    RoomMapper roomMapper;
    @Resource
    BookLectureMapper bookLectureMapper;

    public ResultUtil getLectureByNumber(String lecNumber){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setData(lectureMapper.getLectureByNumber(lecNumber));
        if(resultUtil.getData()==null){
            resultUtil.setSetMessage("未查询到相关信息");
            resultUtil.setCode(201);
            return resultUtil;
        }
        resultUtil.setSetMessage("查询成功");
        resultUtil.setCode(200);
        return resultUtil;
    };

    public ResultUtil getInitTableInfo(){
        ResultUtil resultUtil = new ResultUtil();
        List<Lecture> list = lectureMapper.selectAll();
        if(list==null){
            resultUtil.setCode(201);
            resultUtil.setSetMessage("未查询到相关信息");
            return resultUtil;
        }
        resultUtil.setSetMessage("返回讲座信息");
        resultUtil.setCode(200);
        resultUtil.setData(list);
        return resultUtil;
    }
    /**
     *
     * 创建讲座的同时添加一条宣讲室讲座占用表
     */
    public ResultUtil createNewLecture(Lecture lecture){
        ResultUtil resultUtil = new ResultUtil();
        int status = lectureMapper.insert(lecture);
        if(status==0){
            resultUtil.setSetMessage("添加失败");
            resultUtil.setCode(201);
            return resultUtil;
        }


        //根据讲座选定的宣讲室名称查询该宣讲室拥有的行数和列数
        Room room = roomMapper.selectRoomByRoomName(lecture.getLecRoom());
        int rrow = room.getRowCount();
        int collumn = room.getColCount();

        //设置接下来即将在数据库中插入的数据的参数,创建讲座占用的宣讲室
        LectureRoom lectureRoom = new LectureRoom();
        lectureRoom.setLecNumber(lecture.getLecNumber());
        System.out.println(lecture.getLecNumber());
        lectureRoom.setRoomNumber(lecture.getLecRoom());
        System.out.println(lecture.getLecRoom());
        lectureRoom.setColCount(collumn);
        System.out.println(collumn);
        lectureRoom.setRowCount(rrow);
        System.out.println(rrow);
        int status1 =  lectureRoomMapper.insert(lectureRoom);
        ResultUtil resultUtil1 = new ResultUtil();
        if(status1==0){
            resultUtil1.setSetMessage("讲座对应宣讲室安排失败");
            resultUtil1.setCode(201);
            return resultUtil;
        }
        resultUtil.setSetMessage("发布讲座成功");
        resultUtil.setCode(200);
        return resultUtil;
    }

    public ResultUtil getTopLectureInfo(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setData(bookLectureMapper.topLecture());
        if(resultUtil.getData()==null){
            resultUtil.setCode(201);
            resultUtil.setSetMessage("查询失败");
            return resultUtil;
        }
        resultUtil.setSetMessage("成功获取前讲座排名10的数据");
        resultUtil.setCode(200);
        return resultUtil;
    }


    public ResultUtil getTopStudent(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setData(bookLectureMapper.topStudent());
        if(resultUtil.getData()==null){
            resultUtil.setSetMessage("查询失败");
            resultUtil.setCode(200);
            return resultUtil;
        }
        resultUtil.setSetMessage("成功获取预约讲座次数最多的10人");
        resultUtil.setCode(200);
        return resultUtil;
    }
    public ResultUtil getTopSpeaker(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setData(bookLectureMapper.topSpeaker());
        if(resultUtil.getData()==null){
            resultUtil.setSetMessage("未查询到信息");
            resultUtil.setCode(201);
            return resultUtil;
        }
        resultUtil.setCode(200);
        resultUtil.setSetMessage("获取最受欢迎的前10名讲师");
        return resultUtil;
    }
    public ResultUtil deleteLecture(String lecNumber){
        ResultUtil resultUtil = new ResultUtil();
        int status = lectureMapper.deleteLecture(lecNumber);
        if(status==0){
            resultUtil.setCode(201);
            resultUtil.setSetMessage("删除失败");
            return resultUtil;
        }
        resultUtil.setSetMessage("删除成功");
        return resultUtil;
    }
    public ResultUtil updateLecture(Lecture lecture){
        ResultUtil resultUtil = new ResultUtil();
        int status = lectureMapper.updateLecture(lecture);
        if(status==0){
            resultUtil.setSetMessage("更新失败");
            resultUtil.setCode(201);
        }else{
            resultUtil.setSetMessage("更新成功");
            resultUtil.setCode(200);
        }
        System.out.println(status);
        return resultUtil;
    }

    public ResultUtil getTodayLecture(String lecDate){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setSetMessage("查询今日召开的讲座");
        System.out.println(lecDate);
        resultUtil.setData(lectureMapper.getTodayLecture(lecDate));
        if(resultUtil.getData()==null){
            resultUtil.setSetMessage("没查询到信息");
            resultUtil.setCode(201);
            return resultUtil;
        }
        resultUtil.setSetMessage("查询成功");
        resultUtil.setCode(200);
        return resultUtil;
    }

}
