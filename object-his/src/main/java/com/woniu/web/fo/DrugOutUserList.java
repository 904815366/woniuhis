package com.woniu.web.fo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.converter.UserConverter;
import com.woniu.entity.dto.UserDto;
import com.woniu.entity.po.UserPo;
import com.woniu.mapper.mysql.UserMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class DrugOutUserList {
    ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
    UserConverter userConverter = applicationContext.getBean(UserConverter.class);
    UserMysqlDao userMysqlDao = applicationContext.getBean(UserMysqlDao.class);
    public List<UserDto> exec(){
        QueryWrapper<UserPo> wrapper = new QueryWrapper<>();
        List<UserPo> userPoList = userMysqlDao.selectList(wrapper);
        List<UserDto> userDtoList = userConverter.listFrom(userPoList);
        return userDtoList;
    }
}
