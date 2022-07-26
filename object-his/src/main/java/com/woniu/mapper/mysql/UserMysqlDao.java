package com.woniu.mapper.mysql;

import com.woniu.entity.dto.UserDto;
import com.woniu.entity.po.UserPo;
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
public interface UserMysqlDao extends BaseMapper<UserPo> {
//    @Select("select ue.aa from u_e ue, rbac_user u where ue.uid = u.id and  u.username=#{username}")
    List<String> queryRolePerms(String username);

    @Select("select * from user where name like concat('%',#{name},'%')")
    List<UserDto> selectByName(UserPo userPo);
}
