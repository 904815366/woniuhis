package com.woniu.mapper.redis;

import com.woniu.entity.po.ArrangePo;
import com.woniu.entity.po.UserPo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrangeRedisDao  extends CrudRepository<ArrangePo,Integer>{
}
