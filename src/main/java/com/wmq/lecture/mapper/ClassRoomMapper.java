package com.wmq.lecture.mapper;

import com.wmq.lecture.entity.ClassRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ClassRoomMapper {
    int insert(ClassRoom record);
    List<ClassRoom> selectAll();

    List<ClassRoom> getSeatInfo(String classRoom);
    void buySeatMapper(@Param("seatNumber")String seatNumber,@Param("room_name") String room_name);
}