package com.woniu.web.fo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.entity.converter.RegisterConverter;
import com.woniu.entity.converter.WarnConverter;
import com.woniu.entity.dto.RegisterDto;
import com.woniu.entity.dto.WarnDto;
import com.woniu.entity.po.RegisterPo;
import com.woniu.entity.po.WarnPo;
import com.woniu.mapper.mysql.RegisterMysqlDao;
import com.woniu.mapper.mysql.WarnMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
public class DrugOutList {
    private Integer pageNum;
    private Integer pageSize;
    private String searchName;
    private String searchId;
    private String searchStatus;
    private Integer searchDoctorid;

    ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();


    public PageInfo<WarnDto> exac() {
        WarnMysqlDao drugOutMysqlDao = applicationContext.getBean(WarnMysqlDao.class);
        WarnConverter drugOutConverter = applicationContext.getBean(WarnConverter.class);
        PageHelper.startPage(pageNum, pageSize);
        //条件查询
        QueryWrapper<WarnPo> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(searchName)) {
            wrapper.like("patientname", searchName);
        }
        if (!StringUtils.isEmpty(searchId)) {
            wrapper.like("registerid", searchId);
        }
        if (!StringUtils.isEmpty(searchStatus)) {
            wrapper.like("status", searchStatus);
        }
        if (!StringUtils.isEmpty(searchDoctorid)) {
            wrapper.like("doctorid", searchDoctorid);
        }
        List<WarnPo> drugOutPoList = drugOutMysqlDao.selectList(wrapper);
        PageInfo<WarnPo> pagePoInfo = new PageInfo<>(drugOutPoList);
        PageInfo<WarnDto> pageInfo = drugOutConverter.pageInfoFrom(pagePoInfo);
        return pageInfo;
    }

    public List<RegisterDto> getRegisterList() {
        RegisterMysqlDao registerMysqlDao = applicationContext.getBean(RegisterMysqlDao.class);
        RegisterConverter registerConverter = applicationContext.getBean(RegisterConverter.class);
        QueryWrapper<RegisterPo> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 2);
        List<RegisterPo> registerPoList = registerMysqlDao.selectList(wrapper);
        List<RegisterDto> registerDtoList = registerConverter.from(registerPoList);
        return registerDtoList;
    }
}

