package com.woniu.web.fo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.woniu.entity.converter.DrugReturnConverter;
import com.woniu.entity.dto.DrugDto;
import com.woniu.entity.dto.DrugReturnDto;
import com.woniu.entity.po.DrugPo;
import com.woniu.entity.po.DrugReturnPo;
import com.woniu.mapper.mysql.DrugReturnMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import lombok.Data;
import org.springframework.context.ApplicationContext;

import java.util.List;

@Data
public class DrugReturnList {
    public List<DrugReturnDto> getReturnList() {
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        DrugReturnMysqlDao drugReturnMysqlDao = applicationContext.getBean(DrugReturnMysqlDao.class);
        DrugReturnConverter drugReturnConverter = applicationContext.getBean(DrugReturnConverter.class);
        QueryWrapper<DrugReturnPo> wrapper = new QueryWrapper<>();
        List<DrugReturnPo> drugReturnPoList =drugReturnMysqlDao.selectList(wrapper);
        List<DrugReturnDto> drugReturnDtoList = drugReturnConverter.listFrom(drugReturnPoList);
        return drugReturnDtoList;
    }
}
