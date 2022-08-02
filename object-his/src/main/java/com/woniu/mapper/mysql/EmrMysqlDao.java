package com.woniu.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.entity.dto.EmrDto;
import com.woniu.entity.po.EmrPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmrMysqlDao extends BaseMapper<EmrPo> {
    List<EmrDto> getByRegisterIdList(Integer registerid);

    void createEmr(EmrPo emrPo);
}
