package com.wmq.lecture.mapper;

import com.wmq.lecture.entity.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RoomMapper {
    int insert(Room record);
    List<Room> selectAll();
    Room selectRoomByRoomName(String roomName);
}