package com.woniu.web.fo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.entity.converter.UserConverter;
import com.woniu.entity.dto.UserDto;
import com.woniu.entity.po.UserPo;
import com.woniu.mapper.mysql.UserMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.util.List;
@Data
public class UserList {
    private Integer pageNum;
    private Integer pageSize;
    private String searchName;


    public PageInfo<UserDto> exec(){
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        UserConverter userConverter = applicationContext.getBean(UserConverter.class);
        UserMysqlDao userMysqlDao = applicationContext.getBean(UserMysqlDao.class);

        if (pageNum==null){
            pageNum=1;
        }
        if (pageSize==null){
            pageSize=5;
        }
        PageHelper.startPage(pageNum,pageSize);
        UserPo userPo = new UserPo();
        if(!StringUtils.isEmpty(searchName)){
            userPo.setName(searchName);
        }else {
            userPo.setName("");
        }
//        List<com.woniu.entity.po.User> userList = userMapper.selectList(wrapper);
        List<UserDto> userList = userMysqlDao.selectByName(userPo);
        PageInfo<UserDto> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }
}
