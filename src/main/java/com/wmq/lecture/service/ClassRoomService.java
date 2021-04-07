package com.wmq.lecture.service;

import com.wmq.lecture.entity.BookLecture;
import com.wmq.lecture.entity.ClassRoom;
import com.wmq.lecture.entity.Lecture;
import com.wmq.lecture.mapper.BookLectureMapper;
import com.wmq.lecture.mapper.ClassRoomMapper;
import com.wmq.lecture.mapper.LectureRoomMapper;
import com.wmq.lecture.utils.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lenovo
 */

@Service
public class ClassRoomService {
    @Resource
    LectureRoomMapper lectureRoomMapper;
   @Resource
    ClassRoomMapper classRoomMapper;
   @Resource
   BookLectureMapper bookLectureMapper;

   /**
    *从座位表返回所有的教室的座位信息
    * */
    public ResultUtil getClassRoomInfo(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(200);
        resultUtil.setSetMessage("返回座位的信息");
        resultUtil.setData(classRoomMapper.selectAll());
        return resultUtil;
    }

    /**
     * 向宣讲室表新增一个教室
     * */
    public ResultUtil postClassRoomInfo(ClassRoom classRoom) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(200);
        resultUtil.setSetMessage("上传座位信息");
        classRoomMapper.insert(classRoom);
        return resultUtil;
    }
    /**
     * 当购买座位时，更改座位此时的状态，并且填写 预定座位表相关数据
     * */
    public ResultUtil buySeat(String seatNumber,String roomNamet){
        ResultUtil resultUtil = new ResultUtil();
//        预编译的的Sql语句要需要单引号
        String roomName = "'"+roomNamet+"'";
        lectureRoomMapper.buySeatMapper(seatNumber,roomName);
        resultUtil.setSetMessage("修改作为状态成功");
        System.out.println("修改状态成功");
        resultUtil.setCode(200);
        return resultUtil;
    }

/**
 *
 *  新增一个宣讲室
 */
    public ResultUtil newRoom(ClassRoom room){
        classRoomMapper.insert(room);
        return null;
    }

//    public ClassRoom selectClassRoomByRoomName(String roomName){
//        return classRoomMapper.selectRoomByRoomName(roomName);
//    }
    public ResultUtil getTopRoom(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setData(lectureRoomMapper.topRoom());
        resultUtil.setSetMessage("查询使用最多的两个宣讲室");
        resultUtil.setCode(200);
        return resultUtil;
    }
}
