package com.woniu.service.impl;

import com.woniu.entity.dto.PatientDto;
import com.woniu.entity.po.PatientPo;
import com.woniu.mapper.mysql.PatientMysqlDao;
import com.woniu.service.PatientService;
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
public class PatientServiceImpl extends ServiceImpl<PatientMysqlDao, PatientPo> implements PatientService {
    @Autowired
    private PatientMysqlDao patientMapper;
    @Override
    public PatientDto getByPatientId(Integer id) {
        return patientMapper.getByPatientId(id);
    }
}
