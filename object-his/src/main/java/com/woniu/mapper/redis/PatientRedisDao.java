package com.woniu.mapper.redis;

import com.woniu.entity.dto.PatientDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRedisDao extends CrudRepository<PatientDto,Integer> {
}
