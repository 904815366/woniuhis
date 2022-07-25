package com.woniu.config;

import com.woniu.filter.JwtTokenAuthenticationFilter;
import com.woniu.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userServiceImpl;


    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyLoginFailHandler myLoginFailHandler;


    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;


    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //在内存中配置用户名和密码

//        auth.inMemoryAuthentication()
//                .withUser("tom").password(passwordEncoder.encode("123")).roles("user")
//                .and()
//                .withUser("admin").password(passwordEncoder.encode("123")).roles("admin");
//            auth.userDetailsService(userServiceImpl).passwordEncoder(passwordEncoder);
        auth.authenticationProvider(getDaoAuthenticationProvider());

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //需要放行的url在这里配置,必须要放行/login和/login.html,不然会报错
        http.authorizeRequests().antMatchers("/login", "/login.html")
                .permitAll().anyRequest().authenticated().and().
                // 设置登陆页、登录表单form中action的地址，也就是处理认证请求的路径
                        formLogin().loginPage("/login.html").loginProcessingUrl("/login")
                //登录表单form中密码输入框input的name名，不修改的话默认是password
                .usernameParameter("username").passwordParameter("password")
                //登录认证成功后默认转跳的路径
//                .defaultSuccessUrl("/home")
                //前后端分离登陆成功
                .successHandler(myAuthenticationSuccessHandler)
                //前后端分离登陆失败
                .failureHandler(myLoginFailHandler)
//                .failureUrl("/error1").permitAll();
                .and()
                //前后端分离用户未登录
                .exceptionHandling()
                .authenticationEntryPoint(myAuthenticationEntryPoint)

                .accessDeniedHandler(myAccessDeniedHandler)

                //前后端分离处理注销
                .and().logout().logoutSuccessHandler(myLogoutSuccessHandler);

        http.addFilterBefore(jwtTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        //关闭CSRF跨域

        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(userServiceImpl);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }


}
