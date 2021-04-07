package com.wmq.lecture.entity.resulttype;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author lenovo
 */
@Data
@EntityScan
public class topStudent {
    String name;
    String stuNumber;
    String stuCount;

}
