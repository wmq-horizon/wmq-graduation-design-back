package com.wmq.lecture.service;

import com.wmq.lecture.entity.Users;
import com.wmq.lecture.mapper.UsersMapper;
import com.wmq.lecture.utils.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lenovo
 */
@Service
public class UserService {
    @Resource
    UsersMapper usersMapper;
    public Users getUserByUid(String uid){
        return usersMapper.selectUserByUid(uid);
    }
    public String getRoleByUid(String uid){
        return usersMapper.selectRoleByUid(uid);
    }

    public ResultUtil selectAllUsers(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setSetMessage("查询所有用户的信息");
        resultUtil.setCode(200);
        resultUtil.setData(usersMapper.selectAll());
        return resultUtil;
    }
}


