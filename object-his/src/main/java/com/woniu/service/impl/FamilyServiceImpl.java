package com.woniu.service.impl;

import com.woniu.entity.converter.FamilyConverter;
import com.woniu.entity.dto.FamilyDto;
import com.woniu.entity.po.FamilyPo;
import com.woniu.mapper.mysql.FamilyMysqlDao;
import com.woniu.service.FamilyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.util.ApplicationContextHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
@Service
public class FamilyServiceImpl extends ServiceImpl<FamilyMysqlDao, FamilyPo> implements FamilyService {

    @Override
    public List<FamilyDto> fmlList() {
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        FamilyMysqlDao familyMysqlDao = applicationContext.getBean(FamilyMysqlDao.class);
        FamilyConverter familyConverter = applicationContext.getBean(FamilyConverter.class);
        List<FamilyPo> familyPoList = familyMysqlDao.selectList(null);
        List<FamilyDto> familyDtoList = familyConverter.listFrom(familyPoList);
        return familyDtoList;
    }
}
