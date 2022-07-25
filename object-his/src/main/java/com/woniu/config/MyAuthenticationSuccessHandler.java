package com.woniu.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniu.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private StringRedisTemplate template;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        try {
            String username = request.getParameter("username");
            String strToken = JwtTokenUtil.createSign(username);
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//            ListOperations<String, String> opsForList = template.opsForList();
//            opsForList.leftPush(, )
            String authoritiesStr = authorities.toString();
            template.opsForValue().set(username,authoritiesStr);
//            String authoritiesStr = "";
//            for (GrantedAuthority authority : authorities) {
//                authoritiesStr +=authority+",";
//            }
//            String newAuthoritiesStr = authoritiesStr.substring(0, authoritiesStr.length() - 1);
//            System.out.println(newAuthoritiesStr);


            ResponseResult<String> result = new ResponseResult<>(strToken,"ok",200);

            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(result));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
