package com.woniu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.entity.po.DrugReturnPo;
import com.woniu.mapper.mysql.DrugReturnMysqlDao;
import com.woniu.repository.DrugReturnRepository;
import com.woniu.service.DrugReturnService;
import com.woniu.service.WarndetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugReturnServiceImpl extends ServiceImpl<DrugReturnMysqlDao, DrugReturnPo> implements DrugReturnService{
    @Autowired
    private DrugReturnRepository drugReturnRepository;
    @Autowired
    private WarndetailsService warndetailsService;

    @Override
    public void returnAdd(Integer id, Integer returnnum) {
        drugReturnRepository.returnAdd(id, returnnum);
        warndetailsService.updateNum(id,returnnum);
    }

    @Override
    public void deleteById(String[] idArr) {
        drugReturnRepository.deleteById(idArr);
    }
}
