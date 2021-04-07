package com.wmq.lecture.config;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lenovo
 */
@Configuration
public class ShiroConfig {
    /**
     *自定义Session管理器，shiro默认是基于Session的管理器
     * */
    @Bean
    public DefaultWebSessionManager defaultWebSessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        System.out.println("---------------"+sessionManager.getGlobalSessionTimeout());
        sessionManager.setGlobalSessionTimeout(15*1000*60);
        return sessionManager;
    }
    /**
     *装配1、自定义Realm
     * */
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    /**
     * 配置安全管理器,装配上面配置的bean
     *
     * @return*/
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        securityManager.setSessionManager(defaultWebSessionManager());
        return securityManager;
    }

    /**
     * 配置权限过滤器
     * */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(securityManager());
        //登录失败之后访问页面
        filter.setLoginUrl("/unLogin");
        //未授权允许访问
        filter.setUnauthorizedUrl("/unauthorized");
        Map<String,String> chain = new LinkedHashMap<>();
        //配置任何人可以访问的页面
        //登录Url任何人均可访问
        chain.put("/login","anon");
        chain.put("/registe","anon");
        //登出url在认证后可访问
        chain.put("/logOut","authc");
        chain.put("/student/home","anon");
        chain.put("/student/**","authc");
        chain.put("/admin/**","roles[admin]");
        //设置登录并且记住我之后可以访问
        chain.put("/**","anon");
        filter.setFilterChainDefinitionMap(chain);
        return filter;
    }
}
