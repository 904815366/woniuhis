package com.woniu.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.entity.dto.NursetypeDto;
import com.woniu.entity.po.MoneylistPo;
import com.woniu.entity.po.NursetypePo;
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
public interface MoneylistMysqlDao extends BaseMapper<MoneylistPo> {

}
