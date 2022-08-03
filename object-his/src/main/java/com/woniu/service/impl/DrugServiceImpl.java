package com.woniu.service.impl;

import com.woniu.entity.po.DrugPo;
import com.woniu.mapper.mysql.DrugMysqlDao;
import com.woniu.repository.DrugRepository;
import com.woniu.repository.DrugrecordRepository;
import com.woniu.service.DrugService;
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
public class DrugServiceImpl extends ServiceImpl<DrugMysqlDao, DrugPo> implements DrugService {
    @Autowired
    private DrugRepository drugRepository;
    @Override
    public void updateStock(String[] idArr) {
        drugRepository.updateStock(idArr);
    }

    @Override
    public void updateReturnStock(String[] idArr) {
        drugRepository.updateReturnStock(idArr);
    }
}
