package com.woniu.repository;

import com.woniu.entity.po.EmrPo;
import com.woniu.mapper.mysql.AppointmentMysqlDao;
import com.woniu.mapper.mysql.EmrMysqlDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmrRepository {
    //使用依赖注入生成全参构造方法
    private final EmrMysqlDao emrMysqlDao;

    public void createEmr(EmrPo emrPo){
        emrMysqlDao.createEmr(emrPo);
    }
}
