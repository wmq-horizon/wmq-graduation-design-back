package com.wmq.lecture.service;

import com.wmq.lecture.entity.BookLecture;
import com.wmq.lecture.entity.resulttype.ParticipatedLecture;
import com.wmq.lecture.mapper.BookLectureMapper;
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
public class BookLectureService {
    @Resource
    BookLectureMapper bookLectureMapper;

    public ResultUtil getPastLecture(String date,String time){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setData(bookLectureMapper.getPastLecture(date,time));
        if (resultUtil.getData()==null){
            resultUtil.setSetMessage("未查询到相关信息");
            resultUtil.setCode(201);
        }
        resultUtil.setSetMessage("查询成功");
        resultUtil.setCode(200);
        return resultUtil;
    }
    public ResultUtil getLectureBookers(String lecNumber){
        ResultUtil resultUtil = new ResultUtil();
        List <BookLecture> result = bookLectureMapper.getLectureBookers(lecNumber);
        if(result==null){
            resultUtil.setSetMessage("暂未查询到该讲座的任何预约信息");
            resultUtil.setCode(201);
            return resultUtil;
        }
        resultUtil.setSetMessage("查询成功");
        resultUtil.setData(result);
        return resultUtil;
    }
    public ResultUtil checkSign(String stuNumber,String lecNumber){
        ResultUtil resultUtil = new ResultUtil();
        int status = bookLectureMapper.checkSign(stuNumber,lecNumber);
        if(status==0){
            resultUtil.setCode(201);
            resultUtil.setSetMessage("签到失败");
            return resultUtil;
        }
        resultUtil.setCode(200);
        resultUtil.setSetMessage("学生签成功");
        return resultUtil;
    }

    public ResultUtil participated(String stuNumber){
        System.out.println("stuNumber:"+stuNumber);
        ResultUtil resultUtil = new ResultUtil();
        List<ParticipatedLecture> list = bookLectureMapper.selectParticipatedLecture(stuNumber);
        if(list==null){
            resultUtil.setCode(201);
            resultUtil.setSetMessage("未查询到参与的讲座信息");
            return resultUtil;
        }
        resultUtil.setCode(200);
        resultUtil.setSetMessage("查询成功");
        resultUtil.setData(list);
        return resultUtil;
    }
    public ResultUtil doComments(BookLecture bookLecture){
        ResultUtil resultUtil = new ResultUtil();
        int status = bookLectureMapper.doComment(bookLecture);
        if(status==0){
            resultUtil.setCode(201);
            resultUtil.setSetMessage("评论失败");
            return resultUtil;
        }
        resultUtil.setSetMessage("添加评论成功");
        resultUtil.setCode(200);
        return resultUtil;
    }

    /**
     * 登录的用户需要预定座位的时候则填写bookLecture信息表
     * */
    public ResultUtil bookLecture(BookLecture bookLecture){
        ResultUtil resultUtil = new ResultUtil();
        int status =  bookLectureMapper.insert(bookLecture);
        if(status==0){
           resultUtil.setCode(201);
           resultUtil.setSetMessage("预约失败");
           return resultUtil;
        }
        resultUtil.setCode(200);
        resultUtil.setSetMessage("预约成功");
        return resultUtil;
    }

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
