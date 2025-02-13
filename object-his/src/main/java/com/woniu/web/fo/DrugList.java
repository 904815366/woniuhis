package com.woniu.web.fo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.entity.converter.DrugConverter;
import com.woniu.entity.dto.DrugDto;
import com.woniu.entity.po.DrugPo;
import com.woniu.mapper.mysql.DrugMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
public class DrugList {
    private Integer pageNum;
    private Integer pageSize;
    private String searchName;
    private String searchBatch;

    ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
    DrugMysqlDao drugMysqlDao = applicationContext.getBean(DrugMysqlDao.class);
    DrugConverter drugConverter = applicationContext.getBean(DrugConverter.class);
    public PageInfo<DrugDto> exac(){

        PageHelper.startPage(pageNum,pageSize);
        //条件查询
        QueryWrapper<DrugPo> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(searchName)){
            wrapper.like("name", searchName);
        }
        if(!StringUtils.isEmpty(searchBatch)){
            wrapper.like("batch", searchBatch);
        }
        List<DrugPo> drugPoList =drugMysqlDao.selectList(wrapper);
        PageInfo<DrugPo> pagePoInfo = new PageInfo<>(drugPoList);
        PageInfo<DrugDto> pageInfo = drugConverter.pageInfoFrom(pagePoInfo);
        System.out.println(pageInfo);
        return pageInfo;
    }
    public List<DrugDto> getDrugList(){
        QueryWrapper<DrugPo> wrapper = new QueryWrapper<>();
        List<DrugPo> drugPoList =drugMysqlDao.selectList(wrapper);
        List<DrugDto> drugDtoList = drugConverter.listFrom(drugPoList);
        return drugDtoList;
    }
}
