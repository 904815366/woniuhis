package com.woniu.mapper.mysql;

import com.woniu.entity.dto.WarnDto;
import com.woniu.entity.po.WarnPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
public interface WarnMysqlDao extends BaseMapper<WarnPo> {
    /**
     * 查询患者医嘱
     * @param id
     * @return
     */
    List<WarnDto> selectAllByPatientId(Integer id);

    void updateWarnById(@Param("registerid") Integer registerid,@Param("warntime") Date warntime);
}
