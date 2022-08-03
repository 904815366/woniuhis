package com.woniu.service;

import com.woniu.entity.po.WarndetailsPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
public interface WarndetailsService extends IService<WarndetailsPo> {

    void updateNum(Integer id, Integer returnnum);

    void updateReturnNum(String[] idArr);
}
