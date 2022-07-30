package com.woniu.web.fo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.converter.WarndetailsConverter;
import com.woniu.entity.dto.WarndetailsDto;
import com.woniu.entity.po.WarndetailsPo;
import com.woniu.mapper.mysql.WarndetailsMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class DrugDetailList {
    public List<WarndetailsDto> getWarndetailsList() {
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        WarndetailsMysqlDao warndetailsMysqlDao = applicationContext.getBean(WarndetailsMysqlDao.class);
        WarndetailsConverter warndetailsConverter = applicationContext.getBean(WarndetailsConverter.class);
        QueryWrapper<WarndetailsPo> wrapper = new QueryWrapper<>();
        List<WarndetailsPo> warndetailsPoList = warndetailsMysqlDao.selectList(wrapper);
        List<WarndetailsDto> warndetailsDtoList = warndetailsConverter.listFrom(warndetailsPoList);
        return warndetailsDtoList;
    }
}
