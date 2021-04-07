package com.wmq.lecture.mapper;

import com.wmq.lecture.entity.Room;
import com.wmq.lecture.entity.LectureRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LectureRoomMapper {

    int insert(LectureRoom record);
    List<Room> getSeatInfo(@Param("roomName")String roomName, @Param("lecNumber") String lecNumber);

    List<LectureRoom> selectAll();
    int buySeatMapper(@Param("seatNumber")String seatNumber, @Param("roomName") String roomName);
    List topRoom();
}