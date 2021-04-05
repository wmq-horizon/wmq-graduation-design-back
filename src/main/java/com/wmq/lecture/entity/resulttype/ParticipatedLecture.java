package com.wmq.lecture.entity.resulttype;


import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author horizon
 */
@Data
@EntityScan
public class ParticipatedLecture {
    private String time;
    private String date;
    private Integer score;
    private Integer commented;
    private String comments;
    private String lecRoom;
    private String title;
    private String speaker;
    private String introduction;
    private String content;
}
