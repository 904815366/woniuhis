package com.woniu.repository;

import com.woniu.entity.dto.UserDto;
import com.woniu.entity.po.UserPo;
import com.woniu.mapper.mysql.UserMysqlDao;
import com.woniu.mapper.redis.UserRedisDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    //使用依赖注入生成全参构造方法
    private final UserMysqlDao userMysqlDao;
    private final UserRedisDao userRedisDao;

    /**
     * 修改mysql里的信息,删除redis缓存里的信息
     * @param userModify
     * @return
     */
    public Integer modifyById(UserPo userModify) {
        Integer integer = userMysqlDao.modifyById(userModify);
        if (integer!=0){
            userRedisDao.deleteById(userModify.getId());
        }
        return integer;
    }

    /**
     * 通过id删除用户
     * 删除mysql和redis缓存里的信息
     * @param id
     * @return
     */
    public Integer deleteById(Integer id) {
        Integer i = userMysqlDao.deleteById(id);
        if (i!=0){
            userRedisDao.deleteById(id);
        }
        return i;
    }

    /**
     * 查询数据库中用户名是否唯一
     * @param searchUsername
     * @return
     */
    public UserDto getUserByUsername(String searchUsername) {
        return userMysqlDao.getUserByUsername(searchUsername);
    }

    /**
     * 录入用户信息
     * @param userPo
     * @return
     */
    public Integer addUser(UserPo userPo) {
        return userMysqlDao.insert(userPo);
    }
}
