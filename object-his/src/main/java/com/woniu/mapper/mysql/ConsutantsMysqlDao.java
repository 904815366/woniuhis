package com.woniu.mapper.mysql;

import com.woniu.entity.dto.ConsutantsDto;
import com.woniu.entity.po.ConsutantsPo;
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
public interface ConsutantsMysqlDao extends BaseMapper<ConsutantsPo> {
    List<ConsutantsDto> consultationByApplydeptid(Integer applydeptid);

    List<ConsutantsDto> consultationByReplydeptid(Integer replydeptid);
}
