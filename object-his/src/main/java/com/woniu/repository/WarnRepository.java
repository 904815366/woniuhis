package com.woniu.repository;

import com.woniu.entity.po.WarnPo;
import com.woniu.entity.po.WarndetailsPo;
import com.woniu.mapper.mysql.WarnMysqlDao;
import com.woniu.mapper.mysql.WarndetailsMysqlDao;
import com.woniu.web.fo.AddCpoeFo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@RequiredArgsConstructor
public class WarnRepository {
    //使用依赖注入生成全参构造方法
    private final WarnMysqlDao warnMysqlDao;
    private final WarndetailsMysqlDao warndetailsMysqlDao;

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

    /**
     * 创建医嘱
     * @param addCpoeFo
     */
    public void createCpoe(AddCpoeFo addCpoeFo){
        //新增医嘱
        WarnPo warnPo = new WarnPo();
        warnPo.setPatientname(addCpoeFo.getPatientname());
        warnPo.setRegisterid(addCpoeFo.getPatientid());
        warnPo.setWarntime(addCpoeFo.getWarntime());
        warnPo.setDoctorid(addCpoeFo.getDoctorid());
        warnPo.setFamilyid(addCpoeFo.getFamilyid());
        warnMysqlDao.createWarn(warnPo);

        //新增医嘱详情
        WarndetailsPo warndetailsPo = new WarndetailsPo();
        warndetailsPo.setDrugid(addCpoeFo.getDrugid());
        warndetailsPo.setNum(addCpoeFo.getNum());
        warndetailsPo.setMeasure(addCpoeFo.getMeasure());
        warndetailsPo.setDirections(addCpoeFo.getDirections());
        warndetailsPo.setWarnid(warnPo.getId());
        warndetailsMysqlDao.createWarndetails(warndetailsPo);
    }
}
