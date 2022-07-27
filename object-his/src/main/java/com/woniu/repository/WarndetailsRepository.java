package com.woniu.repository;

import com.woniu.entity.po.WarndetailsPo;
import com.woniu.mapper.mysql.WarndetailsMysqlDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WarndetailsRepository {
    //使用依赖注入生成全参构造方法
    private final WarndetailsMysqlDao warndetailsMysqlDao;

    public void updateWarndetailsByWarnid(WarndetailsPo warndetailsPo){
        warndetailsMysqlDao.updateWarndetailsByWarnid(warndetailsPo);
    }
}
