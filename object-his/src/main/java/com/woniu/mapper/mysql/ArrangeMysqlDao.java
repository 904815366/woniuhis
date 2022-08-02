package com.woniu.mapper.mysql;

import com.woniu.entity.dto.ArrangeDto;
import com.woniu.entity.po.ArrangePo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
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
public interface ArrangeMysqlDao extends BaseMapper<ArrangePo> {

    @Select({"<script> " +
            "select arrange.id,arrange.dutyuserid,arrange.dutytime," +
            "user.name,user.familyid,user.roleid," +
            "role.name rolename , family.familyname " +
            "from arrange , user , role ,family " +
            "where user.roleid = role.id and user.familyid = family.id " +
            "and user.id = arrange.dutyuserid " +
            "and createtime <![CDATA[ <= ]]> (date_sub(curdate(),interval weekday(curdate()) - 6 DAY)) " +
            "and createtime <![CDATA[ >= ]]> (date_sub(curdate(),INTERVAL WEEKDAY(curdate()) + 0 DAY)) " +
            "and user.name like concat('%',#{name},'%') " +
            "<if test='roleid != null and roleid !=\"\"'>" +
            "and roleid=#{roleid} " +
            "</if>" +
            "<if test='familyid != null and familyid !=\"\"'>" +
            "and familyid=#{familyid} " +
            "</if>" +
            "order by dutyuserid" +
            "</script>"})
    List<ArrangeDto> getArrangeList(ArrangeDto arrangeDto);

    @Select({"<script> " +
            "select arrange.id,arrange.dutyuserid,arrange.dutytime," +
            "user.name,user.familyid,user.roleid," +
            "role.name rolename , family.familyname " +
            "from arrange , user , role ,family " +
            "where user.roleid = role.id and user.familyid = family.id " +
            "and user.id = arrange.dutyuserid " +
            "and createtime <![CDATA[ <= ]]> (date_sub(curdate(),interval weekday(curdate()) - 13 DAY)) " +
            "and createtime <![CDATA[ >= ]]> (date_sub(curdate(),INTERVAL WEEKDAY(curdate()) - 7 DAY)) " +
            "and user.name like concat('%',#{name},'%') " +
            "<if test='roleid != null and roleid !=\"\"'>" +
            "and roleid=#{roleid} " +
            "</if>" +
            "<if test='familyid != null and familyid !=\"\"'>" +
            "and familyid=#{familyid} " +
            "</if>" +
            "order by dutyuserid" +
            "</script>"})
    List<ArrangeDto> getArrangeListNext(ArrangeDto arrangeDto);

    @Insert("insert into arrange values (null,#{dutyuserid},#{dutytime}," +
            "(date_sub(curdate(),INTERVAL WEEKDAY(curdate()) -(#{dutytime}-1) DAY)),#{createuserid})")
    Integer addThis(@Param("dutyuserid") Integer dutyuserid,@Param("dutytime") Integer s, @Param("createuserid")Integer createuserid);

    @Insert("insert into arrange values (null,#{dutyuserid},#{dutytime}," +
            "(date_sub(curdate(),INTERVAL WEEKDAY(curdate()) -(#{dutytime}+6) DAY)),#{createuserid})")
    Integer addNext(@Param("dutyuserid") Integer dutyuserid,@Param("dutytime") Integer s, @Param("createuserid") Integer createuserid);

    @Delete("delete from arrange where dutyuserid = #{dutyuserid} " +
            "and createtime <= (date_sub(curdate(),INTERVAL WEEKDAY(curdate()) - 6 DAY)) " +
            "and createtime >= (date_sub(curdate(),INTERVAL WEEKDAY(curdate()) + 0 DAY))")
    Integer delThisByDutyuserid(@Param("dutyuserid") Integer dutyuserid);

    @Delete("delete from arrange where dutyuserid = #{dutyuserid} " +
            "and createtime <= (date_sub(curdate(),INTERVAL WEEKDAY(curdate()) - 13 DAY)) " +
            "and createtime >= (date_sub(curdate(),INTERVAL WEEKDAY(curdate()) - 7 DAY))")
    Integer delNextByDutyuserid(@Param("dutyuserid") Integer dutyuserid);
}
