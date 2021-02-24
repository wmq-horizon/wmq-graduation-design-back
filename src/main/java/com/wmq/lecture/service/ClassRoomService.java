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
    public ResultUtil buySeatSercice(String seatNumber,String room_name){
        ResultUtil resultUtil = new ResultUtil();
        lectureRoomMapper.buySeatMapper(seatNumber,room_name);
        resultUtil.setSetMessage("修改作为状态成功");
        System.out.println("修改状态成功");
        resultUtil.setCode(200);
        return resultUtil;
    }

    /**
     * 登录的用户需要预定座位的时候则填写bookLecture信息表
     * */
    public ResultUtil bookLecture(Lecture lecture){
        ResultUtil resultUtil = new ResultUtil();
        BookLecture bookLecture = new BookLecture();
        Subject currentUser = SecurityUtils.getSubject();
        String uid = (String)currentUser.getPrincipal();
        System.out.println("用户是："+uid);
        bookLecture.setStuNumber(uid);

        bookLecture.setRoomNumber(lecture.getLecRoom());
        System.out.println(lecture.getLecRoom());

        bookLecture.setLecNumber(lecture.getLecNumber());
        System.out.println(lecture.getLecNumber());

        bookLecture.setRrow(Integer.parseInt(lecture.getSpeaker())+1);
        System.out.println(Integer.parseInt(lecture.getSpeaker()));

        bookLecture.setCollumn(Integer.parseInt(lecture.getIntroduction())+1);
        System.out.println(Integer.parseInt(lecture.getIntroduction()));

        bookLecture.setTime(lecture.getLecTime());
        System.out.println(lecture.getLecTime());

        bookLecture.setDate(lecture.getLecDate());
        System.out.println(lecture.getLecDate());

        bookLecture.setScore(lecture.getLecScore());
        System.out.println(lecture.getLecScore());
        bookLectureMapper.insert(bookLecture);
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
