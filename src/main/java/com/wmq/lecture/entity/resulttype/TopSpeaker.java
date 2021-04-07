package com.wmq.lecture.entity.resulttype;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author lenovo
 */
@Data
@EntityScan
public class TopSpeaker {
    private String speaker;
    private String lecNumber;
    private Integer totalLecture;
}
