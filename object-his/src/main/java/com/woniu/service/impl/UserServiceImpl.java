package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.po.User;
import com.woniu.mapper.mysql.UserMapper;
import com.woniu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    private final UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        User user = userMapper.selectOne(wrapper);


        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        if (user == null){
            accountNonExpired = false;
        }
        if (user.getStatus().equals("1")){
            accountNonLocked = false;
        }
        if (user.getStatus().equals("2")){
            enabled = false;
        }

//        List<String> percodes = userMapper.queryRolePerms(user.getUsername());
        List<String> percodes = new ArrayList<>();

        List<GrantedAuthority> authorities = new ArrayList<>();

        percodes.forEach(percode->{
            authorities.add(new SimpleGrantedAuthority(percode));
        });


        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),user.getPassword(),enabled,accountNonExpired,credentialsNonExpired,accountNonLocked,authorities);
        return userDetails;
    }
}
