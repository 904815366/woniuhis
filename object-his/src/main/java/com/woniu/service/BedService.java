package com.woniu.service;

import com.woniu.entity.po.BedPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
public interface BedService extends IService<BedPo> {

    void upBedByidStatus(Integer bid);

    void dowBedByidStatus(Integer bid);

}
