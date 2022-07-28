package com.woniu.service.impl;

import com.woniu.entity.po.BedPo;
import com.woniu.mapper.mysql.BedMysqlDao;
import com.woniu.repository.BedRepository;
import com.woniu.service.BedService;
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
public class BedServiceImpl extends ServiceImpl<BedMysqlDao, BedPo> implements BedService {

    @Autowired
    private BedRepository bedRepository;

    public void upBedByidStatus(Integer bid) {
        bedRepository.upBadStatu(bid);

    }
}
