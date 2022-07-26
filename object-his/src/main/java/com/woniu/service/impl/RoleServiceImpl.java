package com.woniu.service.impl;

import com.woniu.entity.converter.RoleConverter;
import com.woniu.entity.converter.UserConverter;
import com.woniu.entity.dto.RoleDto;
import com.woniu.entity.po.RolePo;
import com.woniu.mapper.mysql.RoleMysqlDao;
import com.woniu.mapper.mysql.UserMysqlDao;
import com.woniu.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.util.ApplicationContextHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

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
public class RoleServiceImpl extends ServiceImpl<RoleMysqlDao, RolePo> implements RoleService {

    @Override
    public List<RoleDto> roleList() {
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        RoleConverter roleConverter = applicationContext.getBean(RoleConverter.class);
        RoleMysqlDao roleMysqlDao = applicationContext.getBean(RoleMysqlDao.class);
        List<RolePo> rolePoList = roleMysqlDao.selectList(null);
        List<RoleDto> roleDtoList = roleConverter.listFrom(rolePoList);
        return roleDtoList;
    }
}
