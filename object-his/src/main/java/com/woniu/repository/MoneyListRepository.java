package com.woniu.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.po.MoneylistPo;
import com.woniu.entity.po.MoneyrecordPo;
import com.woniu.entity.po.RegisterPo;
import com.woniu.mapper.mysql.MoneylistMysqlDao;
import com.woniu.mapper.mysql.MoneyrecordMysqlDao;
import com.woniu.mapper.redis.MoneyrecordRedisDao;
import com.woniu.web.fo.ModifyMoneyListOfStatusComment;
import com.woniu.web.fo.ModifyStatusAndRegisterMoneyComment;
import com.woniu.web.fo.ModifyStatusAndRegisterMoneysComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MoneyListRepository {
    private final MoneylistMysqlDao moneylistMysqlDao;

    public List<MoneylistPo> MoneyListByRegisterIdAndStatusQuery(Integer registerId, Integer status, String consumtime) {
        QueryWrapper<MoneylistPo> wrapper = new QueryWrapper();
        wrapper.eq("registerid", registerId).orderByAsc("consumtime");
        if (status != -1)
            wrapper.eq("status", status);

        if (consumtime != null){
            wrapper.like("consumtime", consumtime.substring(0,consumtime.indexOf(" ")));

        }


        return moneylistMysqlDao.selectList(wrapper);

    }


    public void modifyStatusByIds(ModifyMoneyListOfStatusComment modifyMoneyListOfStatusComment) {
        boolean out = modifyMoneyListOfStatusComment.getOutIds().isEmpty() || "".equals(modifyMoneyListOfStatusComment.getOutIds());
        boolean ret = modifyMoneyListOfStatusComment.getReturnIds().isEmpty() || "".equals(modifyMoneyListOfStatusComment.getReturnIds());
        if (out == false) {
            String[] ids = modifyMoneyListOfStatusComment.getOutIds().substring(0, modifyMoneyListOfStatusComment.getOutIds().length() - 1).split(",");
            moneylistMysqlDao.updateStatusTo1ByIds(ids);
        }
        if (ret == false) {
            String[] ids = modifyMoneyListOfStatusComment.getReturnIds().substring(0, modifyMoneyListOfStatusComment.getReturnIds().length() - 1).split(",");
            moneylistMysqlDao.updateStatusTo3ByIds(ids);
        }
    }

    public void addEverydayMoneyList(List<RegisterPo> registerPos) {
        moneylistMysqlDao.insertEverydayMoneyList(registerPos);
    }

    public void modifyStatusById(ModifyStatusAndRegisterMoneyComment modifyStatusAndRegisterMoneyComment) {
        MoneylistPo po = MoneylistPo.builder().id(modifyStatusAndRegisterMoneyComment.getId()).status(modifyStatusAndRegisterMoneyComment.getStatus()).build();
        int updateById = moneylistMysqlDao.updateById(po);
        if (updateById == 0){
            throw new RuntimeException("modifyStatusById执行结果为0");
        }
    }



    public boolean modifyStatusByIds(ModifyStatusAndRegisterMoneysComment modifyStatusAndRegisterMoneysComment) {
        String[] arr = modifyStatusAndRegisterMoneysComment.getIds().split("-");
        moneylistMysqlDao.updateStatusTo1ByIds(arr);
        return true;
    }
}
