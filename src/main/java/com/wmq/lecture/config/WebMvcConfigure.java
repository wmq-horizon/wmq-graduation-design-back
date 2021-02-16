package com.wmq.lecture.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author 王梦琼
 */
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(-1)
@Component
@ServletComponentScan
public class WebMvcConfigure implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {}
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PATCH,DELETE,PUT,OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        // prefight请求
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus( 200 );
            return;}
        chain.doFilter(request, response);
    }
    @Override
    public void destroy() { }
}