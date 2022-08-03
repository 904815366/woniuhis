package com.woniu.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.po.DrugPo;
import com.woniu.entity.po.DrugReturnPo;
import com.woniu.entity.po.WarndetailsPo;
import com.woniu.mapper.mysql.DrugMysqlDao;
import com.woniu.mapper.mysql.DrugReturnMysqlDao;
import com.woniu.mapper.mysql.WarndetailsMysqlDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DrugRepository {
    //使用依赖注入生成全参构造方法
    private final WarndetailsMysqlDao warndetailsMysqlDao;
    private final DrugMysqlDao drugMysqlDao;
    private final DrugReturnMysqlDao drugReturnMysqlDao;

    public void updateStock(String[] idArr) {
        for (String id : idArr) {
            QueryWrapper<WarndetailsPo> wrapper = new QueryWrapper<>();
            wrapper.eq("warnid", id);
            List<WarndetailsPo> warndetailsPoList = warndetailsMysqlDao.selectList(wrapper);
            WarndetailsPo warndetailsPo = warndetailsPoList.get(0);
            //查询药品列表
            DrugPo drugPo = drugMysqlDao.selectById(warndetailsPo.getDrugid());
            drugPo.setNum(drugPo.getNum()-warndetailsPo.getNum());
            drugMysqlDao.updateById(drugPo);
        }
    }

    public void updateReturnStock(String[] idArr) {
        for (String id : idArr) {
            DrugReturnPo drugReturnPo = drugReturnMysqlDao.selectById(id);
            //查询药品列表
            DrugPo drugPo = drugMysqlDao.selectById(drugReturnPo.getDrugid());
            drugPo.setNum(drugPo.getNum()+drugReturnPo.getNum());
            drugMysqlDao.updateById(drugPo);
        }
    }
}
