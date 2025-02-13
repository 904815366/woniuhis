package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniu.entity.dto.UserDto;
import com.woniu.entity.po.UserPo;
import com.woniu.mapper.mysql.PermsMysqlDao;
import com.woniu.mapper.mysql.UserMysqlDao;
import com.woniu.repository.UserRepository;
import com.woniu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.util.ApplicationContextHolder;
import com.woniu.web.fo.UserModify;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class UserServiceImpl extends ServiceImpl<UserMysqlDao, UserPo> implements UserService, UserDetailsService {

    private final UserMysqlDao userMysqlDao;
    private final StringRedisTemplate template;
    private final PermsMysqlDao permsMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserPo> wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        UserPo userPo = userMysqlDao.selectOne(wrapper);


        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        if (userPo == null){
            accountNonExpired = false;
        }
        if (userPo.getStatus().equals("1")){
            accountNonLocked = false;
        }
        if (userPo.getStatus().equals("2")){
            enabled = false;
        }

        template.opsForValue().set("loginSuccessOfNameAndId", userPo.getName()+"-"+userPo.getId()+"-"+userPo.getFamilyid());
        List<String> percodes = permsMapper.getPermsPercodeByUsername(userPo.getUsername());
//        List<String> percodes = new ArrayList<>();


        List<GrantedAuthority> authorities = new ArrayList<>();

        percodes.forEach(percode->{
            authorities.add(new SimpleGrantedAuthority(percode));
        });

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(
                        userPo.getUsername(), userPo.getPassword(),enabled,accountNonExpired,credentialsNonExpired,accountNonLocked,authorities);

        return userDetails;
    }

    @Override
    public Integer modifyById(UserPo userModify) {
        UserRepository userRepository = ApplicationContextHolder.
                getApplicationContext().getBean(UserRepository.class);
        return userRepository.modifyById(userModify);
    }

    @Override
    public Integer deleteById(Integer id) {
        UserRepository userRepository = ApplicationContextHolder.
                getApplicationContext().getBean(UserRepository.class);
        Integer deleteById = userRepository.deleteById(id);
        if(deleteById==0){
            throw new RuntimeException("删除失败");
        }
        return deleteById;
    }

    @Override
    public UserDto getUserByUsername(String searchUsername){
        UserRepository userRepository = ApplicationContextHolder.
                getApplicationContext().getBean(UserRepository.class);
        return userRepository.getUserByUsername(searchUsername);
    }

    @Override
    public Integer addUser(UserPo userPo) {
        UserRepository userRepository = ApplicationContextHolder.
                getApplicationContext().getBean(UserRepository.class);
        return userRepository.addUser(userPo);
    }

    @Override
    public UserDto getUserById(Integer id) {
        UserRepository userRepository = ApplicationContextHolder.
                getApplicationContext().getBean(UserRepository.class);
        return userRepository.getUserById(id);
    }

    @Override
    public Integer updSelf(Integer id, String password) {
        UserRepository userRepository = ApplicationContextHolder.
                getApplicationContext().getBean(UserRepository.class);
        //密码加密
        PasswordEncoder passwordEncoder = ApplicationContextHolder
                .getApplicationContext().getBean(PasswordEncoder.class);
        String encodePsw = passwordEncoder.encode(password);
        Integer i = userRepository.updSelf(id, encodePsw);
        if (i==0){
            throw new RuntimeException("修改失败");
        }
        return i;
    }

    @Override
    public Boolean psw(String oPsw, String password) {
        //密码比对
        PasswordEncoder passwordEncoder = ApplicationContextHolder
                .getApplicationContext().getBean(PasswordEncoder.class);
        return passwordEncoder.matches(oPsw, password);
    }
}
