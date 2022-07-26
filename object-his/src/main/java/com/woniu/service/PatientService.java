package com.woniu.service;

import com.woniu.entity.dto.PatientDto;
import com.woniu.entity.po.PatientPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
public interface PatientService extends IService<PatientPo> {
    PatientDto getByPatientId(Integer id);
}
