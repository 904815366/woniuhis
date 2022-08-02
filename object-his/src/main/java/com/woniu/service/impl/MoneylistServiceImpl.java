package com.woniu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.woniu.entity.po.MoneylistPo;
import com.woniu.entity.po.RegisterPo;
import com.woniu.mapper.mysql.MoneylistMysqlDao;
import com.woniu.repository.MoneyListRepository;
import com.woniu.repository.RegisterRepository;
import com.woniu.service.MoneylistService;
import com.woniu.service.RegisterService;
import com.woniu.web.fo.ModifyMoneyListOfStatusComment;
import com.woniu.web.fo.ModifyStatusAndRegisterMoneyComment;
import com.woniu.web.fo.ModifyStatusAndRegisterMoneysComment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    private final RegisterService registerService;
    private final EventBus bus;


    public MoneylistServiceImpl(EventBus bus, MoneyListRepository moneyListRepository, RegisterService registerService) {
        bus.register(this); // 向 EventBus 注册自己。去登记"自己"的所有的 @Subscribe 方法。
        this.bus = bus;
        this.moneyListRepository = moneyListRepository;
        this.registerService = registerService;
    }

    @Override
    @Subscribe
    public void modifyStatusByIds(ModifyMoneyListOfStatusComment modifyMoneyListOfStatusComment) {
        moneyListRepository.modifyStatusByIds(modifyMoneyListOfStatusComment);
    }


    /**
     * 定时器任务:新增每日住院费
     */
    @Override
    public void addEverydayMoneyList() {
        List<RegisterPo> registerPos = registerService.queryStatus();
        if (registerPos.size() == 0)
            return;
        moneyListRepository.addEverydayMoneyList(registerPos);
    }

    @Override
    @Subscribe
    public void modifyStatus(ModifyStatusAndRegisterMoneyComment modifyStatusAndRegisterMoneyComment) {
        moneyListRepository.modifyStatusById(modifyStatusAndRegisterMoneyComment);
        registerService.modifyRegisterMoneyAndUsed(modifyStatusAndRegisterMoneyComment);
    }

    @Override
    @Subscribe
    public void modifyStatus(ModifyStatusAndRegisterMoneysComment modifyStatusAndRegisterMoneysComment) {
        registerService.modifyRegisterMoneyAndUsed(
                ModifyStatusAndRegisterMoneyComment.builder()
                        .consummoney(modifyStatusAndRegisterMoneysComment.getSumMoney())
                        .registerid(modifyStatusAndRegisterMoneysComment.getRegisterid()).build());

        boolean modifyStatusByIds = moneyListRepository.modifyStatusByIds(modifyStatusAndRegisterMoneysComment);
        if (modifyStatusByIds == false) {
            throw new RuntimeException("modifyStatusByIds执行失败");
        }

    }
}
