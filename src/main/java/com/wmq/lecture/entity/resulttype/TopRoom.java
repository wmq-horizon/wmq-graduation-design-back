package com.wmq.lecture.entity.resulttype;

import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author lenovo
 */
@EntityScan
public class TopRoom {
//    select room_number,count(*)roomCount from lecture_room
    String roomNumber;
    Integer roomCount;

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }
}
