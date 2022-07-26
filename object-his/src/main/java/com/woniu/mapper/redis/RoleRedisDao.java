package com.woniu.mapper.redis;

import com.woniu.entity.dto.RoleDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRedisDao extends CrudRepository<RoleDto,Integer> {
}
