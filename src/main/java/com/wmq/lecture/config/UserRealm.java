package com.wmq.lecture.config;
import com.wmq.lecture.entity.Users;
import com.wmq.lecture.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import javax.annotation.Resource;

/**
 * @author lenovo
 */
public class UserRealm extends AuthorizingRealm {
    @Resource
    UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        1、将authenticationToken强转为 UsernamePasswordToken
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
//        2、获取token里面的userName
        String uid = token.getUsername();
        System.out.println("2017110336");
        System.out.println(uid);
//        3、根据userName从数据库取出用户名和密码，并根据查询情况抛出一些异常
        Users user = userService.getUserByUid(uid);
        System.out.println("UserRealm里面的user,现在正在登陆");
        System.out.println(uid);
        System.out.println(user);
        if(user==null){
            throw new UnknownAccountException("账户不存在");
        }
//        4、从数据库中取出用户名和密码，由SimpleAuthenticationInfo和token当中的信息进行比对
        Object principal = user.getUid();
        Object credentials = user.getPassword();
//        ByteSource credentialSalt = ByteSource.Util.bytes(principal);
//        Object encipherPassword = new SimpleHash("MD5",passwords,credentialSalt,99);
//        System.out.println("加密之后的密码"+encipherPassword);
        System.out.println("principle"+principal);
        return new SimpleAuthenticationInfo(principal, credentials,getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String uid = (String)principalCollection.getPrimaryPrincipal();
        String role = userService.getRoleByUid(uid );
        System.out.println("获取到的角色为");
        System.out.println(role);
        info.addRole(role);
        System.out.println("授权完成，现在在授权");
        return info;
    }
}
