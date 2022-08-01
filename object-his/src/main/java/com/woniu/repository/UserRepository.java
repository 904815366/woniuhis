package com.woniu.repository;

import com.woniu.entity.converter.UserConverter;
import com.woniu.entity.dto.UserDto;
import com.woniu.entity.po.UserPo;
import com.woniu.mapper.mysql.UserMysqlDao;
import com.woniu.mapper.redis.UserRedisDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    //使用依赖注入生成全参构造方法
    private final UserMysqlDao userMysqlDao;
    private final UserRedisDao userRedisDao;
    private final UserConverter userConverter;

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

    /**
     * 查询单个用户信息
     * @param id
     * @return
     */
    public UserDto getUserById(Integer id) {
        UserDto userDto;
        UserPo userPo;
        try {
            userPo = userRedisDao.findById(id).orElseThrow(NullPointerException::new);
        }catch (NullPointerException e ){
            userPo = userMysqlDao.selectById(id);
            Objects.requireNonNull(userPo, "员工不存在");
            userRedisDao.save(userPo);
        }
        userDto = userConverter.from(userPo);
        return userDto;
    }

    /**
     * 用户修改自己的密码
     * @param id
     * @param password
     * @return
     */
    public Integer updSelf(Integer id, String password) {
        Integer integer = userMysqlDao.updSelf(id, password);
        if (integer==1){
            userRedisDao.deleteById(id);
        }
        return integer;
    }
}
