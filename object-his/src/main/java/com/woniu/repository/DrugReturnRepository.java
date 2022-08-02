package com.woniu.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.po.DrugReturnPo;
import com.woniu.entity.po.WarnPo;
import com.woniu.entity.po.WarndetailsPo;
import com.woniu.mapper.mysql.DrugReturnMysqlDao;
import com.woniu.mapper.mysql.WarnMysqlDao;
import com.woniu.mapper.mysql.WarndetailsMysqlDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DrugReturnRepository {

    private final WarndetailsMysqlDao warndetailsMysqlDao;
    private final DrugReturnMysqlDao drugReturnMysqlDao;
    private final WarnMysqlDao warnMysqlDao;

    /**
     * 添加退药申请
     * @param id
     * @param returnnum
     */
    public void returnAdd(Integer id, Integer returnnum) {
        QueryWrapper<WarndetailsPo> wrapper = new QueryWrapper<>();
        wrapper.eq("warnid", id);
        WarnPo warnPo = warnMysqlDao.selectById(id);
        List<WarndetailsPo> warndetailsPos = warndetailsMysqlDao.selectList(wrapper);
        WarndetailsPo warndetailsPo = warndetailsPos.get(0);
        if (warndetailsPo.getNum()>=returnnum){
            DrugReturnPo drugReturnPo = new DrugReturnPo();
            drugReturnPo.setDrugid(warndetailsPo.getDrugid());
            drugReturnPo.setNum(returnnum);
            drugReturnPo.setWarnid(id);
            drugReturnPo.setStatus("0");
            drugReturnPo.setApplytime(new Date());
            drugReturnPo.setDoctorid(warnPo.getDoctorid());
            drugReturnPo.setNurseid(warnPo.getNurseid());
            drugReturnPo.setFamilyid(warnPo.getFamilyid());
            drugReturnPo.setPatientname(warnPo.getPatientname());
            drugReturnPo.setRegisterid(warnPo.getRegisterid());
            drugReturnMysqlDao.insert(drugReturnPo);
        }else {
            throw new RuntimeException();
        }
    }

    public void deleteById(String[] idArr) {
        for (String id : idArr) {
            drugReturnMysqlDao.deleteById(id);
        }
    }
}
