package com.wmq.lecture.service;

import com.wmq.lecture.entity.Room;
import com.wmq.lecture.mapper.BookLectureMapper;
import com.wmq.lecture.mapper.RoomMapper;
import com.wmq.lecture.mapper.LectureRoomMapper;
import com.wmq.lecture.utils.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 */

@Service
public class ClassRoomService {
    @Resource
    LectureRoomMapper lectureRoomMapper;
   @Resource
   RoomMapper roomMapper;
   @Resource
   BookLectureMapper bookLectureMapper;

   /**
    *从座位表返回所有的教室的座位信息
    * */
    public ResultUtil getClassRoomInfo(){
        ResultUtil resultUtil = new ResultUtil();
        List<Room> rooms = roomMapper.selectAll();
        if(rooms==null){
            resultUtil.setCode(201);
            resultUtil.setSetMessage("查询宣讲室信息失败");
            return resultUtil;
        }
        resultUtil.setData(rooms);
        resultUtil.setCode(200);
        resultUtil.setSetMessage("查询宣讲室信息成功");
        return resultUtil;
    }

    /**
     * 向宣讲室表新增一个教室
     * */
    public ResultUtil postClassRoomInfo(Room room) {
        ResultUtil resultUtil = new ResultUtil();
        int status = roomMapper.insert(room);
        if(status==0){
            resultUtil.setCode(201);
            resultUtil.setSetMessage("添加失败");
            return resultUtil;
        }
        resultUtil.setCode(200);
        resultUtil.setSetMessage("添加座位信息成功");
        return resultUtil;
    }
    /**
     * 当购买座位时，更改座位此时的状态，并且填写 预定座位表相关数据
     * */
    public ResultUtil buySeat(String seatNumber,String roomNamet,String lecNumber){
        ResultUtil resultUtil = new ResultUtil();
        //        预编译的的Sql语句要需要单引号
        String roomName = "'"+roomNamet+"'";
        int status = lectureRoomMapper.buySeatMapper(seatNumber,roomNamet,lecNumber);
        if(status==0){
            resultUtil.setSetMessage("预定座位失败");
            resultUtil.setCode(201);
            return resultUtil;
        }
        resultUtil.setSetMessage("修改座位状态成功");
        System.out.println("修改状态成功");
        resultUtil.setCode(200);
        return resultUtil;
    }

    /**
     *
     *  新增一个宣讲室
     */
    public ResultUtil newRoom(Room room){
        ResultUtil resultUtil = new ResultUtil();
        int status = roomMapper.insert(room);
        if(status == 0){
            resultUtil.setCode(201);
            resultUtil.setSetMessage("新建失败");
            return resultUtil;
        }
        resultUtil.setSetMessage("创建成功");
        resultUtil.setCode(200);
        return resultUtil;
    }
    public ResultUtil getTopRoom(){
        ResultUtil resultUtil = new ResultUtil();
        List rooms = lectureRoomMapper.topRoom();
        if(rooms==null){
            resultUtil.setSetMessage("没有查询到相关信息");
            resultUtil.setCode(201);
            return resultUtil;
        }
        resultUtil.setData(rooms);
        resultUtil.setSetMessage("查询成功");
        resultUtil.setCode(200);
        return resultUtil;
    }
    public ResultUtil updateRoom(Room room){
        ResultUtil resultUtil = new ResultUtil();
        int status = roomMapper.updateRoom(room);
        if(status==0){
            resultUtil.setSetMessage("修改失败");
            resultUtil.setCode(201);
            return resultUtil;
        }
        resultUtil.setSetMessage("修改成功");
        resultUtil.setCode(200);
        return resultUtil;
    }

    public ResultUtil deleteRoomByNumber(String roomNumber){
        ResultUtil resultUtil = new ResultUtil();
        int status = roomMapper.deleteRoomByNumber(roomNumber);
        if(status==0){
            resultUtil.setSetMessage("删除失败");
            resultUtil.setCode(201);
            return resultUtil;
        };
        resultUtil.setSetMessage("删除成功");
        resultUtil.setCode(200);
        return resultUtil;
    }
    public ResultUtil getRoomName(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setData(roomMapper.getRoomNames());
        if(resultUtil.getData()!=null){
            resultUtil.setSetMessage("查询成功");
            resultUtil.setCode(200);
            return resultUtil;
        }
        resultUtil.setSetMessage("查询失败！");
        resultUtil.setCode(201);
        return resultUtil;
    }
}
