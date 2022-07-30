package com.woniu.service.impl;

import com.woniu.entity.po.ConsutantsPo;
import com.woniu.mapper.mysql.ConsutantsMysqlDao;
import com.woniu.repository.ConsutantsRepository;
import com.woniu.service.ConsutantsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ConsutantsServiceImpl extends ServiceImpl<ConsutantsMysqlDao, ConsutantsPo> implements ConsutantsService {
    @Autowired
    private ConsutantsRepository consutantsRepository;
    @Override
    public void addApplyConsultation(ConsutantsPo consutantsPo) {
        consutantsRepository.addApplyConsultation(consutantsPo);
    }

    @Override
    public void updateReplyConsultation(ConsutantsPo consutantsPo) {
        consutantsRepository.updateReplyConsultation(consutantsPo);
    }

    @Override
    public void updateApplyConsultation(ConsutantsPo consutantsPo) {
        consutantsRepository.updateApplyConsultation(consutantsPo);
    }

    @Override
    public void removeById(Integer id) {
        consutantsRepository.removeById(id);
    }
}
