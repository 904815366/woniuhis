package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.po.DrugrecordPo;
import com.woniu.entity.po.WarnPo;
import com.woniu.entity.po.WarndetailsPo;
import com.woniu.mapper.mysql.WarnMysqlDao;
import com.woniu.repository.WarnRepository;
import com.woniu.repository.WarndetailsRepository;
import com.woniu.service.DrugrecordService;
import com.woniu.service.WarnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.service.WarndetailsService;
import com.woniu.web.fo.AddCpoeFo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
@Transactional
public class WarnServiceImpl extends ServiceImpl<WarnMysqlDao, WarnPo> implements WarnService {
    @Autowired
    private WarnRepository warnRepository;
    @Autowired
    private WarndetailsRepository  warndetailsRepository;
    @Override
    public void updateWarn(Integer registerid, Date warntime, WarndetailsPo warndetailsPo) {
        warnRepository.updateWarn(registerid, warntime);
        warndetailsRepository.updateWarndetailsByWarnid(warndetailsPo);
    }

    @Override
    public void deleteWarn(Integer id) {
        warnRepository.deleteWarnById(id);
        warndetailsRepository.deleteByWarnid(id);
    }

    @Override
    public void updatestatus(String[] ids,Integer status) {
        warnRepository.updateWarnStatus(ids,status);
    }

    @Override
    public void addCpoe(AddCpoeFo addCpoeFo) {
        warnRepository.createCpoe(addCpoeFo);
    }

}
