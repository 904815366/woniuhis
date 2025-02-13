package com.woniu.service;

import com.woniu.entity.po.ArrangePo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
public interface ArrangeService extends IService<ArrangePo> {

    void addArr(Integer dutyuserid, String checkListStr,Integer createuserid,String week);

    void editArr(Integer dutyuserid, String checkListStr, Integer createuserid, String week);
}
