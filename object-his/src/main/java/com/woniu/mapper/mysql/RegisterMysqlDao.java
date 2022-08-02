package com.woniu.mapper.mysql;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.dto.RegisterByFamilyDto;
import com.woniu.entity.dto.RegisterByWarnidDto;
import com.woniu.entity.dto.RegisterDto;
import com.woniu.entity.po.RegisterPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.web.fo.InsertMoneyRecordComment;
import com.woniu.web.fo.ModifyRegisterMoneyComment;
import com.woniu.web.fo.ModifyStatusAndRegisterMoneyComment;
import com.woniu.web.fo.OutSettlementComment;
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

    @Update("UPDATE register set refund=refund+money-(#{notMoney}-#{returnMoney}),used=used+(#{notMoney}-#{returnMoney}),money=0,status=4 WHERE id=#{registerId}")
    boolean UpdateMoneyAndStatusById(OutSettlementComment outSettlementComment);

    @Update("UPDATE register set money=money-#{consummoney},used=used+#{consummoney} WHERE id=#{registerid}")
    Integer updateRegisterMoneyAndUsed(ModifyStatusAndRegisterMoneyComment modifyStatusAndRegisterMoneyComment);

    @Select("select id,name from register where familyid=#{familyid}")
    List<RegisterByFamilyDto> queryRegiPByFarmiy(Integer familyid);

    @Select("select w.id from warn w,register r where w.registerid=r.id and r.id=#{rid}")
    List<RegisterByWarnidDto> queryByWarnid(Integer rid);
}
