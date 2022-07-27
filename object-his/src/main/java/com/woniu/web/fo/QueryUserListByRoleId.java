package com.woniu.web.fo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.converter.UserConverter;
import com.woniu.entity.dto.UserDto;
import com.woniu.entity.po.UserPo;
import com.woniu.mapper.mysql.UserMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import lombok.Data;
import org.springframework.context.ApplicationContext;

import java.util.List;

@Data
public class QueryUserListByRoleId {
    private final Integer ROLEID = 4;

    public List<UserDto> exec(){
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        UserConverter userConverter = applicationContext.getBean(UserConverter.class);
        UserMysqlDao userMysqlDao = applicationContext.getBean(UserMysqlDao.class);
        QueryWrapper<UserPo> wrapper = new QueryWrapper<>();
        wrapper.eq("roleid",ROLEID);
        List<UserPo> pos = userMysqlDao.selectList(wrapper);
        List<UserDto> dtos = userConverter.listFrom(pos);
        return dtos;
    }
}
