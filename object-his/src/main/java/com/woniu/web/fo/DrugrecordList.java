package com.woniu.web.fo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.entity.converter.DrugrecordConverter;
import com.woniu.entity.dto.DrugDto;
import com.woniu.entity.dto.DrugrecordDto;
import com.woniu.entity.po.DrugPo;
import com.woniu.entity.po.DrugrecordPo;
import com.woniu.mapper.mysql.DrugrecordMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
public class DrugrecordList {
    private Integer pageNum;
    private Integer pageSize;
    private String searchName;
    private String searchType;
    private Integer searchFamilyid;

    public PageInfo<DrugrecordDto> exac(){
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        DrugrecordMysqlDao drugrecordMysqlDao = applicationContext.getBean(DrugrecordMysqlDao.class);
        DrugrecordConverter drugrecordConverter = applicationContext.getBean(DrugrecordConverter.class);
        PageHelper.startPage(pageNum,pageSize);
        //条件查询
        QueryWrapper<DrugrecordPo> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(searchName)){
            wrapper.like("name", searchName);
        }
        if(!StringUtils.isEmpty(searchType)){
            wrapper.like("type", searchType);
        }
        if(!StringUtils.isEmpty(searchFamilyid)){
            wrapper.like("familyid", searchFamilyid);
        }
        List<DrugrecordPo> drugrecordPoList =drugrecordMysqlDao.selectList(wrapper);
        PageInfo<DrugrecordPo> pagePoInfo = new PageInfo<>(drugrecordPoList);
        PageInfo<DrugrecordDto> pageInfo = drugrecordConverter.pageInfoFrom(pagePoInfo);
        System.out.println(pageInfo);
        return pageInfo;
    }

}
