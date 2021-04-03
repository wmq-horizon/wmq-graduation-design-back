package com.wmq.lecture.mapper;

import com.wmq.lecture.entity.Lecture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lenovo
 */
@Mapper
public interface LectureMapper {
    /**
     * @mbg.generated Tue Jan 12 17:56:06 CST 2021
     */
    int insert(Lecture record);
    /**
     * @mbg.generated Tue Jan 12 17:56:06 CST 2021
     */
    List<Lecture> selectAll();

    void deleteLecture(String lecNumber);
    int updateLecture(Lecture lecture);

    List<Lecture> getTodayLecture(String lecDate);
}