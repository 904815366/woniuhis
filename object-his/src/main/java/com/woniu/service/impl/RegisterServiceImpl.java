package com.woniu.service.impl;

import com.woniu.entity.po.RegisterPo;
import com.woniu.mapper.mysql.RegisterMysqlDao;
import com.woniu.service.RegisterService;
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
public class RegisterServiceImpl extends ServiceImpl<RegisterMysqlDao, RegisterPo> implements RegisterService {

}
