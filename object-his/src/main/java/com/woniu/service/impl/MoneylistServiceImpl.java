package com.woniu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.entity.po.MoneylistPo;
import com.woniu.mapper.mysql.MoneylistMysqlDao;
import com.woniu.service.MoneylistService;
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
public class MoneylistServiceImpl extends ServiceImpl<MoneylistMysqlDao, MoneylistPo> implements MoneylistService {

}
