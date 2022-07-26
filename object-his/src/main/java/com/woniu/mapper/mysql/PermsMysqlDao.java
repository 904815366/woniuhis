package com.woniu.mapper.mysql;

import com.woniu.entity.dto.PermsDto;
import com.woniu.entity.po.PermsPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
@Mapper
public interface PermsMysqlDao extends BaseMapper<PermsPo> {

    @Select("SELECT * from " +
            "perms p,role r,role_perms rp,user u " +
            "where u.roleid=r.id " +
            "and r.id=rp.roleid " +
            "and rp.permsid=p.id " +
            "and u.username=#{username}")
    List<PermsDto> getPermsListByUsername(String username);

    @Select("SELECT p.percode from " +
            "perms p,role r,role_perms rp,user u " +
            "where u.roleid=r.id " +
            "and r.id=rp.roleid " +
            "and rp.permsid=p.id " +
            "and u.username=#{username}")
    List<String> getPermsPercodeByUsername(String username);
}
