package com.woniu.service.impl;

import com.woniu.entity.po.PermsPo;
import com.woniu.mapper.mysql.PermsMysqlDao;
import com.woniu.service.PermsService;
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
public class PermsServiceImpl extends ServiceImpl<PermsMysqlDao, PermsPo> implements PermsService {

}
