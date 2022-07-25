package com.woniu.mapper.mysql;

import com.woniu.entity.dto.PatientDto;
import com.woniu.entity.po.Patient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
@Mapper
public interface PatientMapper extends BaseMapper<Patient> {

    /**
     * 查询在院患者列表
     */
    List<PatientDto> getPatientList(String name);

    PatientDto getByPatientId(Integer id);

}
