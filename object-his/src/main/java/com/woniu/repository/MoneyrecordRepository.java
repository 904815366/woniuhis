package com.woniu.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.dto.PatientDto;
import com.woniu.entity.po.MoneyrecordPo;
import com.woniu.mapper.mysql.MoneyrecordMysqlDao;
import com.woniu.mapper.mysql.PatientMysqlDao;
import com.woniu.mapper.redis.MoneyrecordRedisDao;
import com.woniu.mapper.redis.PatientRedisDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.FastByteArrayOutputStream;

import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class MoneyrecordRepository {
    //使用依赖注入生成全参构造方法
    private final MoneyrecordMysqlDao moneyrecordMysqlDao;
    private final MoneyrecordRedisDao moneyrecordRedisDao;


    public List<MoneyrecordPo> queryListById(Integer id) {
        QueryWrapper<MoneyrecordPo> wrapper = new QueryWrapper<>();
        wrapper.eq("registerid",id);
        wrapper.orderByDesc("prepaytime");
        List<MoneyrecordPo> pos = moneyrecordMysqlDao.selectList(wrapper);
        return pos;
    }

    public boolean addOne(MoneyrecordPo po) {
        int insert = moneyrecordMysqlDao.insert(po);
        if (insert == 0){
            return false;
        }
        return true;
    }
}
