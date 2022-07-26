package com.woniu.repository;

import com.woniu.mapper.mysql.RoleMysqlDao;
import com.woniu.mapper.redis.RoleRedisDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleRepository {
    private final RoleMysqlDao roleMysqlDao;
    private final RoleRedisDao roleRedisDao;
}
