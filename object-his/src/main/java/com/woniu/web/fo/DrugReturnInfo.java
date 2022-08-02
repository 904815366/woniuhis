package com.woniu.web.fo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.entity.converter.DrugReturnConverter;
import com.woniu.entity.converter.WarndetailsConverter;
import com.woniu.entity.dto.DrugDto;
import com.woniu.entity.dto.DrugReturnDto;
import com.woniu.entity.dto.WarndetailsDto;
import com.woniu.entity.po.DrugPo;
import com.woniu.entity.po.DrugReturnPo;
import com.woniu.entity.po.WarndetailsPo;
import com.woniu.mapper.mysql.DrugReturnMysqlDao;
import com.woniu.mapper.mysql.WarndetailsMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.util.List;
@Data
public class DrugReturnInfo {
    private Integer pageNum;
    private Integer pageSize;
    private String searchName;
    private String searchId;
    public PageInfo<DrugReturnDto> getReturnInfo() {
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        DrugReturnMysqlDao drugReturnMysqlDao = applicationContext.getBean(DrugReturnMysqlDao.class);
        DrugReturnConverter drugReturnConverter = applicationContext.getBean(DrugReturnConverter.class);
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<DrugReturnPo> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(searchName)) {
            wrapper.like("patientname", searchName);
        }
        if (!StringUtils.isEmpty(searchId)) {
            wrapper.like("registerid", searchId);
        }
        List<DrugReturnPo> drugReturnPoList = drugReturnMysqlDao.selectList(wrapper);
        PageInfo<DrugReturnPo> pagePoInfo = new PageInfo<>(drugReturnPoList);
        PageInfo<DrugReturnDto> pageInfo = drugReturnConverter.pageInfoFrom(pagePoInfo);
        return pageInfo;
    }

}
