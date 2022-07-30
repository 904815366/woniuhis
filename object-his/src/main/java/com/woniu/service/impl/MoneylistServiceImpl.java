package com.woniu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.woniu.entity.po.MoneylistPo;
import com.woniu.mapper.mysql.MoneylistMysqlDao;
import com.woniu.repository.MoneyListRepository;
import com.woniu.repository.RegisterRepository;
import com.woniu.service.MoneylistService;
import com.woniu.web.fo.ModifyMoneyListOfStatusComment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
@Service
@Transactional
public class MoneylistServiceImpl extends ServiceImpl<MoneylistMysqlDao, MoneylistPo> implements MoneylistService {

    private final MoneyListRepository moneyListRepository;

    private final EventBus bus;


    public MoneylistServiceImpl(EventBus bus, MoneyListRepository moneyListRepository) {
        bus.register(this); // 向 EventBus 注册自己。去登记"自己"的所有的 @Subscribe 方法。
        this.bus = bus;
        this.moneyListRepository = moneyListRepository;
    }

    @Override
    @Subscribe
    public void modifyStatusByIds(ModifyMoneyListOfStatusComment modifyMoneyListOfStatusComment) {
        moneyListRepository.modifyStatusByIds(modifyMoneyListOfStatusComment);
    }
}
