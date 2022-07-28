package com.woniu.mapper.redis;

import com.woniu.entity.dto.RoleDto;
import com.woniu.entity.po.MoneyrecordPo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyrecordRedisDao extends CrudRepository<MoneyrecordPo,Integer> {
}
