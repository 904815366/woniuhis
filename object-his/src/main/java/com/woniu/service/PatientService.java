package com.woniu.service;

import com.woniu.entity.dto.PatientDto;
import com.woniu.entity.po.Patient;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
public interface PatientService extends IService<Patient> {
    PatientDto getByPatientId(Integer id);
}
