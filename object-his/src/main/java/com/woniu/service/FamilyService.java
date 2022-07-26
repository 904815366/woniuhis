package com.woniu.service;

import com.woniu.entity.dto.FamilyDto;
import com.woniu.entity.po.FamilyPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
public interface FamilyService extends IService<FamilyPo> {

    List<FamilyDto> fmlList();
}
