package com.wmq.lecture.mapper;

import com.wmq.lecture.entity.ClassRoom;
import com.wmq.lecture.entity.LectureRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LectureRoomMapper {

    int insert(LectureRoom record);
    List<ClassRoom> getSeatInfo(@Param("roomName")String roomName,@Param("lecNumber") String lecNumber);

    List<LectureRoom> selectAll();
    void buySeatMapper(@Param("seatNumber")String seatNumber, @Param("room_name") String room_name);
}