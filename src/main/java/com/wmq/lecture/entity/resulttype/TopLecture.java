package com.wmq.lecture.entity.resulttype;

import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author lenovo
 */
@EntityScan
public class TopLecture {
    private String title;
    private String lecNumber;
    private Integer totalLecture;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
