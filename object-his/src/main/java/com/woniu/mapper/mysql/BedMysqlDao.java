package com.woniu.mapper.mysql;

import com.woniu.entity.po.BedPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.entity.po.FamilyPo;
import com.woniu.entity.po.WardPo;
import org.apache.ibatis.annotations.Mapper;
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
public interface BedMysqlDao extends BaseMapper<BedPo> {

    @Select("select  * from family")//查询出所有的科室
    List<FamilyPo> qureyFamiy();

    @Select("select * from ward where familyid=#{fid}")//通过科室Id查询出所有病房
    List<WardPo> qureyWardByfid(Integer fid);

    @Select("select * from bed where wardid=#{wid} and status='0'")
    List<BedPo> qureyBedBywid(Integer wid);

    @Update("update bed set status='1' where id=#{bid}")
    void upBadstatu(Integer bid);
}
