package com.woniu.web.fo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.po.DrugPo;
import com.woniu.mapper.mysql.DrugMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Data
public class DrugAdd {
    private String name;
    private String type;
    private Double price;
    private Integer num;
    private String producer;
    private String producing;
    private String mdfunction;
    private String memo;
    private Date protime;
    private Date validtime;
    private Integer alarmnum;
    private String batch;

    public void exac(){
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        DrugMysqlDao drugMysqlDao = applicationContext.getBean(DrugMysqlDao.class);
        DrugPo drugPo = new DrugPo();
        if(!StringUtils.isEmpty(batch) &&!StringUtils.isEmpty(num)){
            drugPo.setBatch(batch);
            QueryWrapper<DrugPo> wrapper = new QueryWrapper<>();
            wrapper.eq("batch", batch);
            List<DrugPo> drugPos = drugMysqlDao.selectList(wrapper);
            if (drugPos.size()!=0){
                DrugPo drugPo1 = drugPos.get(0);
                drugPo1.setNum(drugPo1.getNum()+num);
                drugMysqlDao.updateById(drugPo1);
            }else {
                if(!StringUtils.isEmpty(name) &&
                   !StringUtils.isEmpty(type) &&
                   !StringUtils.isEmpty(price) &&
                   !StringUtils.isEmpty(producer) &&
                   !StringUtils.isEmpty(producing) &&
                   !StringUtils.isEmpty(mdfunction) &&
                   !StringUtils.isEmpty(protime) &&
                   !StringUtils.isEmpty(validtime) &&
                   !StringUtils.isEmpty(alarmnum)
                ){
                    drugPo.setBatch(batch);
                    drugPo.setName(name);
                    drugPo.setNum(num);
                    drugPo.setType(type);
                    drugPo.setPrice(price);
                    drugPo.setProducer(producer);
                    drugPo.setProducing(producing);
                    drugPo.setMdfunction(mdfunction);
                    drugPo.setProtime(protime);
                    drugPo.setValidtime(validtime);
                    drugPo.setAlarmnum(alarmnum);
                    drugMysqlDao.insert(drugPo);
                }else {
                    throw new RuntimeException();
                }
            }
        }else{
            throw new RuntimeException();
        }
    }
}
