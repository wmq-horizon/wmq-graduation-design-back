package com.wmq.lecture.mapper;

import com.wmq.lecture.entity.BookLecture;
import com.wmq.lecture.entity.resulttype.ParticipatedLecture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @author horizon
 */
@Mapper
public interface BookLectureMapper {
    /**
     * param record
     * return  int
     **/
    int insert(BookLecture record) throws SQLIntegrityConstraintViolationException;
    /**
     * return  List<BookLecture>
     **/
    List<BookLecture> selectAll();

    List topLecture();

    List topStudent();

    List topSpeaker();

    List<BookLecture> getLectureBookers(String lecNumber);

    /**
     * desc:根据学号和讲座编号在bookLecture表中检查学生，用update方法
     * */
    int checkSign(@Param("stuNumber") String stuNumber, @Param("lecNumber") String lecNumber,@Param("score") String score);

    List<ParticipatedLecture> selectParticipatedLecture(String stuNumber);

    int doComment(BookLecture participatedLecture);

    List<BookLecture>getPastLecture(@Param("date")String date,@Param("time")String time);
}