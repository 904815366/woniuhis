package com.woniu.service;

import com.woniu.entity.po.WarnPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.entity.po.WarndetailsPo;
import com.woniu.web.fo.AddCpoeFo;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
public interface WarnService extends IService<WarnPo> {
    void updateWarn(Integer registerid, Date warntime, WarndetailsPo warndetailsPo);

    void deleteWarn(Integer id);

    void updatestatus(String[] ids);

    void addCpoe(AddCpoeFo addCpoeFo);
}
