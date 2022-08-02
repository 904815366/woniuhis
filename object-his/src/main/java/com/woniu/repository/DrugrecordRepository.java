package com.woniu.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.po.*;
import com.woniu.mapper.mysql.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DrugrecordRepository {

    //使用依赖注入生成全参构造方法
    private final WarnMysqlDao warnMysqlDao;
    private final WarndetailsMysqlDao warndetailsMysqlDao;
    private final DrugrecordMysqlDao drugrecordMysqlDao;
    private final DrugReturnMysqlDao drugReturnMysqlDao;
    private final DrugMysqlDao drugMysqlDao;

    public void addRecord(String[] idArr,Integer userid,String type) {
        for (String id : idArr) {
            WarnPo warnPo = warnMysqlDao.selectById(id);
            QueryWrapper<WarndetailsPo> wrapper = new QueryWrapper<>();
            wrapper.eq("warnid", id);
            List<WarndetailsPo> warndetailsPoList = warndetailsMysqlDao.selectList(wrapper);
            WarndetailsPo warndetailsPo = warndetailsPoList.get(0);
            DrugPo drugPo = drugMysqlDao.selectById(warndetailsPo.getDrugid());
            //添加药品记录
            DrugrecordPo drugrecordPo = new DrugrecordPo();
            drugrecordPo.setDrugid(warndetailsPo.getDrugid());
            drugrecordPo.setNum(warndetailsPo.getNum());
            System.out.println(type);
            drugrecordPo.setType(type);
            drugrecordPo.setTime(new Date());
            drugrecordPo.setUserid(userid);
            drugrecordPo.setName(drugPo.getName());
            drugrecordPo.setWarndetailsid(warndetailsPo.getId());
            drugrecordPo.setDoctorid(warnPo.getDoctorid());
            drugrecordPo.setNurseid(warnPo.getNurseid());
            drugrecordPo.setFamilyid(warnPo.getFamilyid());
            drugrecordMysqlDao.insert(drugrecordPo);
        }
    }

    public void addReturnRecord(String[] idArr, Integer userid) {
        for (String id : idArr) {
            DrugReturnPo drugReturnPo = drugReturnMysqlDao.selectById(id);
            DrugPo drugPo = drugMysqlDao.selectById(drugReturnPo.getDrugid());
            //添加药品记录
            DrugrecordPo drugrecordPo = new DrugrecordPo();
            drugrecordPo.setDrugid(drugReturnPo.getDrugid());
            drugrecordPo.setNum(drugReturnPo.getNum());
            drugrecordPo.setType("2");
            drugrecordPo.setTime(new Date());
            drugrecordPo.setUserid(userid);
            drugrecordPo.setName(drugPo.getName());
            drugrecordPo.setDrugreturnid(drugReturnPo.getId());
            drugrecordPo.setDoctorid(drugReturnPo.getDoctorid());
            drugrecordPo.setNurseid(drugReturnPo.getNurseid());
            drugrecordPo.setFamilyid(drugReturnPo.getFamilyid());
            drugrecordMysqlDao.insert(drugrecordPo);
        }
    }
}
