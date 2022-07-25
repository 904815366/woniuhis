package com.woniu.mapper.mysql;

import com.woniu.entity.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
public interface UserMapper extends BaseMapper<User> {
//    @Select("select ue.aa from u_e ue, rbac_user u where ue.uid = u.id and  u.username=#{username}")
    List<String> queryRolePerms(String username);
}
