package com.woniu.service.impl;

import com.woniu.entity.po.DrugrecordPo;
import com.woniu.mapper.mysql.DrugrecordMysqlDao;
import com.woniu.repository.DrugrecordRepository;
import com.woniu.repository.MoneyListRepository;
import com.woniu.repository.WarnRepository;
import com.woniu.repository.WarndetailsRepository;
import com.woniu.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
@Service
public class DrugrecordServiceImpl extends ServiceImpl<DrugrecordMysqlDao, DrugrecordPo> implements DrugrecordService {
    @Autowired
    private MoneylistService moneylistService;
    @Autowired
    private DrugrecordRepository drugrecordRepository;
    @Autowired
    private WarnService warnService;
    @Autowired
    private DrugService drugService;
    @Autowired
    private DrugReturnService drugReturnService;
    @Autowired
    private WarndetailsService warndetailsService;

    @Override
    public void addDrugrecord(String[] idArr,Integer userid,Integer status,String type,Integer nurseid) {
        warnService.updatestatus(idArr,status,null);
        drugrecordRepository.addRecord(idArr,userid,type);
        moneylistService.addDrugList(idArr);
        drugService.updateStock(idArr);
    }

    @Override
    public void addDrugReturnrecord(String[] idArr, Integer userid) {
        drugrecordRepository.addReturnRecord(idArr,userid);
        moneylistService.addDrugReturnList(idArr);
        drugService.updateReturnStock(idArr);
        drugReturnService.deleteById(idArr);

    }

    @Override
    public void addDrugRefuserecord(String[] idArr) {
        warndetailsService.updateReturnNum(idArr);
        drugReturnService.deleteById(idArr);
    }
}
