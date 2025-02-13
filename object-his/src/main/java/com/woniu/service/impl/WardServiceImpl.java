package com.woniu.service.impl;

import com.woniu.entity.po.WardPo;
import com.woniu.mapper.mysql.WardMysqlDao;
import com.woniu.service.WardService;
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
public class WardServiceImpl extends ServiceImpl<WardMysqlDao, WardPo> implements WardService {

}
