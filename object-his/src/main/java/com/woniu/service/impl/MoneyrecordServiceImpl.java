package com.woniu.service.impl;

import com.woniu.entity.po.MoneyrecordPo;
import com.woniu.mapper.mysql.MoneyrecordMysqlDao;
import com.woniu.service.MoneyrecordService;
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
public class MoneyrecordServiceImpl extends ServiceImpl<MoneyrecordMysqlDao, MoneyrecordPo> implements MoneyrecordService {

}
