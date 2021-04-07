package com.wmq.lecture.entity.resulttype;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author lenovo
 */
@Data
@EntityScan
public class TopRoom {
//    select room_number,count(*)roomCount from lecture_room
    String roomNumber;
    Integer roomCount;
}
