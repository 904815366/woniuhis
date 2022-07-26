package com.woniu.mapper.redis;

import com.woniu.entity.dto.UserDto;
import com.woniu.entity.po.UserPo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRedisDao extends CrudRepository<UserPo,Integer> {
}
