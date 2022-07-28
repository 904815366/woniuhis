package com.woniu.mapper.redis;

import com.woniu.entity.po.NurserecordPo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NurseecordRedis extends CrudRepository<NurserecordPo,Integer> {
}
