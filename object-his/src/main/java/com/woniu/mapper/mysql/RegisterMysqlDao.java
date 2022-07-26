package com.woniu.mapper.mysql;

import com.woniu.entity.po.RegisterPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
@Mapper
public interface RegisterMysqlDao extends BaseMapper<RegisterPo> {


    List<RegisterPo> queryRegisters(@Param("pid") Integer pid);

    @Select("select * from register where patientid=#{patientid}")
    RegisterPo queryRegisterByPatientid(Integer patientid);

}
