package com.woniu.mapper.mysql;

import com.woniu.entity.po.WarndetailsPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
@Mapper
public interface WarndetailsMysqlDao extends BaseMapper<WarndetailsPo> {
    void updateWarndetailsByWarnid(WarndetailsPo warndetailsPo);

    void deleteByWarnid(Integer warnid);

    void createWarndetails(WarndetailsPo warndetailsPo);
}
