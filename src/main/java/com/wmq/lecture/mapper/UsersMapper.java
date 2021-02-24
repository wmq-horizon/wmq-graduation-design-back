package com.wmq.lecture.mapper;

import com.wmq.lecture.entity.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersMapper {
    int deleteByPrimaryKey(String uid);
    int insert(Users record);
    Users selectByPrimaryKey(String uid);
    List<Users> selectAll();
    int updateByPrimaryKey(Users record);
    Users selectUserByUid(String uid);
    String selectRoleByUid(String uid);
    int insertExcelData(List<Users> item);
    List topScoreUser();
    List topIntegrityUser();
}