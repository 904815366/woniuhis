package com.woniu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.entity.po.EmrPo;
import com.woniu.mapper.mysql.EmrMysqlDao;
import com.woniu.repository.EmrRepository;
import com.woniu.service.EmrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmrServiceImpl extends ServiceImpl<EmrMysqlDao, EmrPo> implements EmrService {
    @Autowired
    private EmrRepository emrRepository;
    @Override
    public void createEmr(EmrPo emrPo) {
        emrRepository.createEmr(emrPo);
    }
}
