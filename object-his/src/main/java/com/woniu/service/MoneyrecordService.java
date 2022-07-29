package com.woniu.service;

import com.woniu.entity.po.MoneyrecordPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.web.fo.InsertMoneyRecordComment;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
public interface MoneyrecordService extends IService<MoneyrecordPo> {

    boolean addOne(InsertMoneyRecordComment comment);
}
