package com.woniu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.entity.po.MoneylistPo;
import com.woniu.entity.po.NursetypePo;
import com.woniu.web.fo.ModifyMoneyListOfStatusComment;
import com.woniu.web.fo.ModifyStatusAndRegisterMoneyComment;
import com.woniu.web.fo.ModifyStatusAndRegisterMoneysComment;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */

public interface MoneylistService extends IService<MoneylistPo> {
    public void modifyStatusByIds(ModifyMoneyListOfStatusComment modifyMoneyListOfStatusComment);

    void addEverydayMoneyList();

    void modifyStatus(ModifyStatusAndRegisterMoneyComment modifyStatusAndRegisterMoneyComment);

    void modifyStatus(ModifyStatusAndRegisterMoneysComment modifyStatusAndRegisterMoneysComment);


    void addDrugList(String[] idArr);

    void addDrugReturnList(String[] idArr);
}
