package com.woniu.mapper.mysql;

import com.woniu.entity.dto.NursetypeDto;
import com.woniu.entity.po.NursetypePo;
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
public interface NursetypeMysqlDao extends BaseMapper<NursetypePo> {

    @Select("select * from nursetype")
    List<NursetypeDto> queryNuresetype();

}
