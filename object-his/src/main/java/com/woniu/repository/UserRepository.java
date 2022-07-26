package com.woniu.repository;

import com.woniu.entity.po.UserPo;
import com.woniu.mapper.mysql.PatientMysqlDao;
import com.woniu.mapper.mysql.UserMysqlDao;
import com.woniu.mapper.redis.PatientRedisDao;
import com.woniu.mapper.redis.UserRedisDao;
import com.woniu.web.fo.UserModify;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    //使用依赖注入生成全参构造方法
    private final UserMysqlDao userMysqlDao;
    private final UserRedisDao userRedisDao;

    public Integer modifyById(UserPo userModify) {
        Integer integer = userMysqlDao.modifyById(userModify);
        if (integer!=0){
            userRedisDao.deleteById(userModify.getId());
        }
        return integer;
    }
}
