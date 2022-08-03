package com.woniu.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.po.DrugReturnPo;
import com.woniu.entity.po.WarndetailsPo;
import com.woniu.mapper.mysql.DrugReturnMysqlDao;
import com.woniu.mapper.mysql.WarndetailsMysqlDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WarndetailsRepository {
    //使用依赖注入生成全参构造方法
    private final WarndetailsMysqlDao warndetailsMysqlDao;
    private final DrugReturnMysqlDao drugReturnMysqlDao;

    public void updateWarndetailsByWarnid(WarndetailsPo warndetailsPo){
        warndetailsMysqlDao.updateWarndetailsByWarnid(warndetailsPo);
    }

    public void deleteByWarnid(Integer Warnid){
        warndetailsMysqlDao.deleteByWarnid(Warnid);
    }

    public void updateNum(Integer id, Integer returnnum) {
        QueryWrapper<WarndetailsPo> wrapper = new QueryWrapper<>();
        wrapper.eq("warnid", id);
        List<WarndetailsPo> poList = warndetailsMysqlDao.selectList(wrapper);
        WarndetailsPo warndetailsPo = poList.get(0);
        warndetailsPo.setNum(warndetailsPo.getNum()-returnnum);
        warndetailsMysqlDao.updateWarndetailsByWarnid(warndetailsPo);
    }

    public void updateReturnNum(String[] idArr) {
        for (String id : idArr) {
            DrugReturnPo drugReturnPo = drugReturnMysqlDao.selectById(id);
            QueryWrapper<WarndetailsPo> wrapper = new QueryWrapper<>();
            wrapper.eq("warnid", drugReturnPo.getWarnid());
            List<WarndetailsPo> poList = warndetailsMysqlDao.selectList(wrapper);
            WarndetailsPo warndetailsPo = poList.get(0);
            warndetailsPo.setNum(warndetailsPo.getNum()+drugReturnPo.getNum());
            warndetailsMysqlDao.updateWarndetailsByWarnid(warndetailsPo);
        }
    }
}
