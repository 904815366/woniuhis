package com.woniu.service;

import com.woniu.entity.po.DrugPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
public interface DrugService extends IService<DrugPo> {

    void updateStock(String[] idArr);

    void updateReturnStock(String[] idArr);
}
