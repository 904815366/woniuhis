package com.woniu.service.impl;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.woniu.config.ResponseResult;
import com.woniu.entity.po.MoneyrecordPo;
import com.woniu.mapper.mysql.MoneyrecordMysqlDao;
import com.woniu.repository.MoneyrecordRepository;
import com.woniu.service.MoneyrecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.service.RegisterService;
import com.woniu.util.ApplicationContextHolder;
import com.woniu.web.fo.InsertMoneyRecordComment;
import com.woniu.web.fo.ModifyRegisterMoneyComment;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
@Service
//@RequiredArgsConstructor
@Transactional
public class MoneyrecordServiceImpl extends ServiceImpl<MoneyrecordMysqlDao, MoneyrecordPo> implements MoneyrecordService {

    private final EventBus bus;
    private final MoneyrecordRepository moneyrecordRepository;
    private final RegisterService registerService;

    public MoneyrecordServiceImpl(EventBus bus, MoneyrecordRepository moneyrecordRepository, RegisterService registerService) {
        bus.register(this); // 向 EventBus 注册自己。去登记"自己"的所有的 @Subscribe 方法。
        this.bus = bus;
        this.moneyrecordRepository = moneyrecordRepository;
        this.registerService = registerService;
    }



    @Subscribe
    public boolean addOne(InsertMoneyRecordComment comment) {

        MoneyrecordPo po = MoneyrecordPo.builder().registerid(comment.getRegisterid()).prepaymoney(comment.getPrepaymoney())
                .prepaytime(comment.getPrepaytime()).userid(comment.getUserid()).build();
        boolean addOne = moneyrecordRepository.addOne(po);
        if (addOne == false){
            throw new RuntimeException("不知道为什么,添加失败了..");
        }
        ModifyRegisterMoneyComment modifyRegisterMoneyComment = new ModifyRegisterMoneyComment(comment.getRegisterid(), comment.getPrepaymoney());
        bus.post(modifyRegisterMoneyComment);
        return true;

    }

}
