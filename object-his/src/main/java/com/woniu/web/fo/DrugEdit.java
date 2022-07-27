package com.woniu.web.fo;

import com.woniu.entity.po.DrugPo;
import com.woniu.mapper.mysql.DrugMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

@Data
public class DrugEdit {
    private Integer id;
    private Integer alarmnum;


    public String exac(){
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        DrugMysqlDao drugMysqlDao = applicationContext.getBean(DrugMysqlDao.class);
        DrugPo drugPo = new DrugPo();
        System.out.println(id);
        drugPo.setId(id);
        if(!StringUtils.isEmpty(alarmnum)){
            drugPo.setAlarmnum(alarmnum);
        }
        try {
            drugMysqlDao.updateById(drugPo);
            return "修改成功";
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }
}
