package com.wmq.lecture.controller;

import com.wmq.lecture.entity.Users;
import com.wmq.lecture.service.UserService;
import com.wmq.lecture.utils.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author 负责登录注册相关的业务
 */
@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class UserController<UploadExcelFileService> {
    @Resource
    UserService userService;

    @PostMapping("/registe")
    public ResultUtil registe(@RequestBody @Validated Users user){
        System.out.println(user.toString());
        return userService.registe(user);
    }

    @RequestMapping("/user/login")
    public ResultUtil userLogin(@RequestBody Users user){
        Subject currentUser = SecurityUtils.getSubject();
        String uid = user.getUid();
        String password = user.getPassword();
        String role = userService.getRoleByUid(uid);
        System.out.println("role:"+role);
        ResultUtil resultUtil = new ResultUtil();
        if(role==null||!role.equals(user.getRole())){
            resultUtil.setCode(243);
            resultUtil.setSetMessage("角色不匹配");
            return resultUtil;
        }
        System.out.println(uid);
        System.out.println("密码为"+password);
        UsernamePasswordToken token = new UsernamePasswordToken(uid,password);

        if(!currentUser.isAuthenticated()&&!currentUser.isRemembered()){
            try{
                currentUser.login(token);
                resultUtil.setCode(200);

                resultUtil.setData(uid);
                resultUtil.setSetMessage(role);
                return resultUtil;
            }catch (UnknownAccountException ue){
                resultUtil.setCode(240);
                resultUtil.setSetMessage("账户不存在");
                System.out.println("账户不存在");
                return resultUtil;
            }catch (IncorrectCredentialsException ie){
                resultUtil.setCode(241);
                resultUtil.setSetMessage("密码错误");
                System.out.println("密码错误");
                return resultUtil;
            }catch (AuthenticationException ae){
                resultUtil.setCode(242);
                resultUtil.setSetMessage("登录失败");
                System.out.println("登录失败");
                return resultUtil;
            }
        }else{
            resultUtil.setSetMessage("已经登录");
            resultUtil.setCode(200);
            return resultUtil;
        }
    }
    @RequestMapping("/user/logOut")
    public ResultUtil logOut(){
        Subject currentUser = SecurityUtils.getSubject();
        System.out.println("现在退出的用户是"+currentUser.toString());
        currentUser.logout();
        return null;
    }
    @RequestMapping("/user/unauthorized")
    public ResultUtil unAuthorized(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(401);
        resultUtil.setSetMessage("未认证");
        return resultUtil;
    }
    @RequestMapping("/student/hello")
    public ResultUtil showWhoIAm(){
        ResultUtil resultUtil = new ResultUtil();
        Subject currentUser = SecurityUtils.getSubject();
        String uid = (String)currentUser.getPrincipal();
        resultUtil.setCode(401);
        resultUtil.setSetMessage("现在登录的是");
        resultUtil.setData(userService.getUserByUid(uid));
        return resultUtil;
    }
    @RequestMapping("/admin/users")
    public ResultUtil selectUsers(){
        return  userService.selectAllUsers();
    }
/**
 * 上传文件用的Controller
 *
 */

    @PostMapping("/upload/excelFile")
    @ResponseBody
    public ResultUtil uploadExcel(MultipartFile file) {
        ResultUtil resultUtil = new ResultUtil();
        try {
            if (file == null) {
                // 文件不能为空
                resultUtil.setCode(310);
                resultUtil.setSetMessage("文件不能为空");
                return resultUtil;
            }

            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
            if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
                // 文件格式不正确
                resultUtil.setCode(311);
                resultUtil.setSetMessage("文件格式不对");
                return resultUtil;
            }

            long size = file.getSize();
            if (size == 0) {
                // 文件不能为空
               resultUtil.setCode(312);
               resultUtil.setSetMessage("文件不能为空");
               return resultUtil;
            }

            resultUtil = userService.uploadExcel(fileName, file);

            if (resultUtil.getSetMessage().equals("success")) {
                //保存成功
                resultUtil.setCode(200);
                resultUtil.setSetMessage("上传文件成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultUtil.setCode(313);
            resultUtil.setSetMessage("上传文件失败");
        }

        return resultUtil;
    }

    @RequestMapping("/student/topScoreUser")
    public ResultUtil getTopScoreStudent(){
        return userService.topScoreUser();
    }
    @RequestMapping("/student/topIntegrityUser")
    public ResultUtil getTopIntegrityStudent(){
        return userService.topIntegrityUser();
    }

}
