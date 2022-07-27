package com.woniu.mapper.mysql;

import com.woniu.entity.po.WarndetailsPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
public interface WarndetailsMysqlDao extends BaseMapper<WarndetailsPo> {
    void updateWarndetailsByWarnid(WarndetailsPo warndetailsPo);

    void deleteByWarnid(Integer warnid);
}
