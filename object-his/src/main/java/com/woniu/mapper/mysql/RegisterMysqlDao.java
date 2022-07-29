package com.woniu.mapper.mysql;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.dto.RegisterDto;
import com.woniu.entity.po.RegisterPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.web.fo.InsertMoneyRecordComment;
import com.woniu.web.fo.ModifyRegisterMoneyComment;
import org.apache.ibatis.annotations.Mapper;
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
@Mapper
public interface RegisterMysqlDao extends BaseMapper<RegisterPo> {


    List<RegisterPo> queryRegisters(@Param("pid") Integer pid);

    @Select("select * from register where patientid=#{patientid}")
    RegisterPo queryRegisterByPatientid(Integer patientid);

    void upRegister(RegisterDto registerDto);

    @Update("update register set status=#{status} where id=#{id}")
    Boolean upRegisterByStatus(@Param("id") Integer id,@Param("status") String status);


    @Update("UPDATE register set money=money+#{prepaymoney} WHERE id=#{registerid}")
    boolean modifyMoney(ModifyRegisterMoneyComment modifyRegisterMoneyComment);
}
