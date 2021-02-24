package com.wmq.lecture.entity.resulttype;

import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author lenovo
 */
@EntityScan
public class topStudent {
    String name;
    String stuNumber;
    String stuCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getStuCount() {
        return stuCount;
    }

    public void setStuCount(String stuCount) {
        this.stuCount = stuCount;
    }
}
