package com.woniu.mapper.redis;

import com.woniu.entity.po.RegisterPo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRedis extends CrudRepository<RegisterPo,Integer> {

}
