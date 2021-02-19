package com.wmq.lecture.mapper;

import com.wmq.lecture.entity.ClassRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ClassRoomMapper {
    int insert(ClassRoom record);
    List<ClassRoom> selectAll();
    ClassRoom selectRoomByRoomName(String roomName);
}