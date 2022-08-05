package com.woniu.mapper.mysql;

import com.woniu.entity.dto.NullArrUserDto;
import com.woniu.entity.dto.UserByFamilyDto;
import com.woniu.entity.dto.UserDto;
import com.woniu.entity.po.UserPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.web.fo.UserModify;
import org.apache.ibatis.annotations.Param;
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

    @Select("select * from user where username = #{searchUsername}")
    UserDto getUserByUsername(@Param("searchUsername") String searchUsername);

    @Select("<script>select id,name,roleid,familyid " +
            "from user where id not in " +
            "(select distinct dutyuserid from arrange " +
            "where createtime <![CDATA[ <= ]]> (date_sub(curdate(),INTERVAL WEEKDAY(curdate()) - 6 DAY)) " +
            "and createtime <![CDATA[ >= ]]> (date_sub(curdate(),INTERVAL WEEKDAY(curdate()) + 0 DAY))) " +
            "and name like concat('%',#{name},'%') and user.status = 0 " +
            "<if test='roleid != null and roleid !=\"\"'>" +
            "and roleid=#{roleid} " +
            "</if>" +
            "<if test='familyid != null and familyid !=\"\"'>" +
            "and familyid=#{familyid} " +
            "</if> </script>")
    List<NullArrUserDto> getThisList(UserPo userPo);

    @Select("<script>select id,name,roleid,familyid " +
            "from user where id not in " +
            "(select distinct dutyuserid from arrange " +
            "where createtime <![CDATA[ <= ]]> (date_sub(curdate(),INTERVAL WEEKDAY(curdate()) - 13 DAY)) " +
            "and createtime <![CDATA[ >= ]]> (date_sub(curdate(),INTERVAL WEEKDAY(curdate()) - 7 DAY))) " +
            "and name like concat('%',#{name},'%') and user.status = 0 " +
            "<if test='roleid != null and roleid !=\"\"'>" +
            "and roleid=#{roleid} " +
            "</if>" +
            "<if test='familyid != null and familyid !=\"\"'>" +
            "and familyid=#{familyid} " +
            "</if> </script>")
    List<NullArrUserDto> getNextList(UserPo userPo);

    @Update("update user set password=#{password} where id = #{id}")
    Integer updSelf(@Param("id")Integer id,@Param("password")String password);

//    xk根据科室查询当前科室所有医生
    @Select("select id,name,familyid,roleid  from user where familyid=#{fid} and roleid=1 ")
     List<UserByFamilyDto> queryUserFlyByFid(Integer fid);
}
