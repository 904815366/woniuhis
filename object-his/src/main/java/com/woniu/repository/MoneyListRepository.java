package com.woniu.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.po.MoneylistPo;
import com.woniu.entity.po.MoneyrecordPo;
import com.woniu.mapper.mysql.MoneylistMysqlDao;
import com.woniu.mapper.mysql.MoneyrecordMysqlDao;
import com.woniu.mapper.redis.MoneyrecordRedisDao;
import com.woniu.web.fo.ModifyMoneyListOfStatusComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MoneyListRepository {
    private final MoneylistMysqlDao moneylistMysqlDao;

    public List<MoneylistPo> MoneyListByRegisterIdAndStatusQuery(Integer registerId, Integer status) {
        QueryWrapper<MoneylistPo> wrapper = new QueryWrapper();
        wrapper.eq("registerid",registerId).orderByAsc("consumtime");
        if (status != -1)
            wrapper.eq("status",status);

        return moneylistMysqlDao.selectList(wrapper);

    }


    public void modifyStatusByIds(ModifyMoneyListOfStatusComment modifyMoneyListOfStatusComment) {
        moneylistMysqlDao.updateStatusByIds(modifyMoneyListOfStatusComment.getIds());
    }
}
