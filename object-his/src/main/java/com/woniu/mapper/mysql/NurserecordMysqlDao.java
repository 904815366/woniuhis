package com.woniu.mapper.mysql;

import com.woniu.entity.dto.NurserecordDto;
import com.woniu.entity.po.NurserecordPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
public interface NurserecordMysqlDao extends BaseMapper<NurserecordPo> {

    List<NurserecordDto> querNurserecords();

}
