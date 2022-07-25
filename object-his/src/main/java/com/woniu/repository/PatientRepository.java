package com.woniu.repository;

import com.woniu.entity.dto.PatientDto;
import com.woniu.mapper.mysql.PatientMapper;
import com.woniu.mapper.redis.PatientRedisDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PatientRepository {
    //使用依赖注入生成全参构造方法
    private final PatientMapper patientDao;
    private final PatientRedisDao patientRedisDao;

    /**
     * 根据id查询患者
     * @param id
     * @return
     */
    public PatientDto selectByPatientId(Integer id){
        PatientDto patientDto =null;
        try {
            patientDto = patientRedisDao.findById(id).get();
            Objects.requireNonNull(patientDto, "患者不存在");
        } catch (Exception e) {
            e.printStackTrace();
            patientDto = patientDao.getByPatientId(id);
            patientRedisDao.save(patientDto);
        }
        return patientDto;
    }
}
