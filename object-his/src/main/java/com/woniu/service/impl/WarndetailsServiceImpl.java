package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.po.WarndetailsPo;
import com.woniu.mapper.mysql.WarndetailsMysqlDao;
import com.woniu.repository.WarndetailsRepository;
import com.woniu.service.WarndetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class WarndetailsServiceImpl extends ServiceImpl<WarndetailsMysqlDao, WarndetailsPo> implements WarndetailsService {
    @Autowired
    private WarndetailsRepository warndetailsRepository;
    @Override
    public void updateNum(Integer id, Integer returnnum) {
        warndetailsRepository.updateNum(id,returnnum);
    }

    @Override
    public void updateReturnNum(String[] idArr) {
        warndetailsRepository.updateReturnNum(idArr);
    }
}
