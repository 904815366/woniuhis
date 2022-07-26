package com.woniu.service.impl;

import com.woniu.entity.po.RolePoPo;
import com.woniu.mapper.mysql.RoleMysqlDao;
import com.woniu.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMysqlDao, RolePoPo> implements RoleService {

}
