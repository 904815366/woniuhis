package com.woniu.filter;

import com.woniu.config.MyLoginFailHandler;
import com.woniu.exception.MyTokenIsInvalidException;
import com.woniu.mapper.mysql.UserMapper;
import com.woniu.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MyLoginFailHandler myLoginFailHandler;

    @Autowired
    private StringRedisTemplate template;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String url = request.getRequestURI();
        if (url.endsWith("/login") || url.endsWith("/login.html")) {
            filterChain.doFilter(request, response);
            return;
        }

        String strToken = request.getHeader("strToken");
        if (StringUtils.isEmpty(strToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            if (JwtTokenUtil.checkSign(strToken)) {
                String username = JwtTokenUtil.getUserId(strToken);


                //通过键:username获取redis内存中的权限字符串
                String authoritiesStr = template.opsForValue().get(username);
                authoritiesStr = authoritiesStr.replace(" ", "")
                        .replace("[", "")
                        .replace("]", "");

                String[] authoritiesArr = authoritiesStr.split(",");

                List<GrantedAuthority> authorities = new ArrayList<>();
                for (String s : authoritiesArr) {
                    authorities.add(new SimpleGrantedAuthority(s));
                }

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(username, "", authorities);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                filterChain.doFilter(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            myLoginFailHandler.onAuthenticationFailure(request, response,
                    new MyTokenIsInvalidException("Token is Invalid!"));
        }
    }
}
