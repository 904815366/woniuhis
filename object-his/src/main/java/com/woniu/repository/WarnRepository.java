package com.woniu.repository;

import com.woniu.mapper.mysql.WarnMysqlDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@RequiredArgsConstructor
public class WarnRepository {
    //使用依赖注入生成全参构造方法
    private final WarnMysqlDao warnMysqlDao;

    /**
     * 修改医嘱表
     * @param registerid
     * @param warntime
     */
    public void updateWarn(Integer registerid, Date warntime){
        warnMysqlDao.updateWarnById(registerid,warntime);
    }

    /**
     * 删除医嘱
     * @param id
     */
    public void deleteWarnById(Integer id){
        warnMysqlDao.deleteWarnById(id);
    }

    /**
     * 修改医嘱状态
     * @param ids
     */
    public void updateWarnStatus(String[] ids){
        warnMysqlDao.updateWarnStatus(ids);
    }
}
