package com.wmq.lecture.entity.resulttype;

import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author lenovo
 */
@EntityScan
public class TopSpeaker {
    private String speaker;
    private String lecNumber;
    private Integer totalLecture;

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getLecNumber() {
        return lecNumber;
    }

    public void setLecNumber(String lecNumber) {
        this.lecNumber = lecNumber;
    }

    public Integer getTotalLecture() {
        return totalLecture;
    }

    public void setTotalLecture(Integer totalLecture) {
        this.totalLecture = totalLecture;
    }
}
