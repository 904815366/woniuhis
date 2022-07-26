package com.woniu.mapper.mysql;

import com.woniu.entity.dto.UserDto;
import com.woniu.entity.po.UserPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.web.fo.UserModify;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Select({"<script> select * from user where name like concat('%',#{name},'%') " +
            "<if test='status != null and status !=\"\"'>" +
            "and status=#{status}" +
            "</if> </script>"})
    List<UserDto> selectByNameAndStatus(UserPo userPo);

    @Update("update user set familyid=#{familyid},name=#{name},sex=#{sex},age=#{age}," +
            "card=#{card},phone=#{phone},roleid=#{roleid},level=#{level}," +
            "status=#{status} where id=#{id}")
    Integer modifyById(UserPo userModify);
}
