package com.woniu.web.fo;

import com.woniu.entity.converter.UserConverter;
import com.woniu.entity.dto.ArrangeDto;
import com.woniu.entity.po.ArrangePo;
import com.woniu.mapper.mysql.ArrangeMysqlDao;
import com.woniu.mapper.mysql.UserMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
public class HomeArrangeList {
    private String searchName;
    private Integer searchRoleid;
    private Integer searchFamilyid;

    public List<ArrangeDto> arrangeList() {
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        ArrangeMysqlDao arrangeMysqlDao = applicationContext.getBean(ArrangeMysqlDao.class);
        ArrangeDto arrangeDto = new ArrangeDto();
        if(!StringUtils.isEmpty(searchName)){
            arrangeDto.setName(searchName);
        }else {
            arrangeDto.setName("");
        }
        if (!StringUtils.isEmpty(searchRoleid)){
            arrangeDto.setRoleid(searchRoleid);
        }
        if (!StringUtils.isEmpty(searchFamilyid)){
            arrangeDto.setFamilyid(searchFamilyid);
        }
        List<ArrangeDto> arrangeList = arrangeMysqlDao.getArrangeList(arrangeDto);
        return arrangeList;
    }

    public List<ArrangeDto> arrangeListNext() {
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        ArrangeMysqlDao arrangeMysqlDao = applicationContext.getBean(ArrangeMysqlDao.class);
        ArrangeDto arrangeDto = new ArrangeDto();
        if(!StringUtils.isEmpty(searchName)){
            arrangeDto.setName(searchName);
        }else {
            arrangeDto.setName("");
        }
        if (!StringUtils.isEmpty(searchRoleid)){
            arrangeDto.setRoleid(searchRoleid);
        }
        if (!StringUtils.isEmpty(searchFamilyid)){
            arrangeDto.setFamilyid(searchFamilyid);
        }
        List<ArrangeDto> arrangeList = arrangeMysqlDao.getArrangeListNext(arrangeDto);
        return arrangeList;
    }
}
