package com.woniu.service;

import com.woniu.entity.po.RegisterPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
public interface RegisterService  {
    PageInfo<RegisterPo> getRegisters(Integer pageNum, Integer pageSize, Integer pid);
}
