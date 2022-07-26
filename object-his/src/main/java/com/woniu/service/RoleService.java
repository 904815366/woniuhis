package com.woniu.service;

import com.woniu.entity.dto.RoleDto;
import com.woniu.entity.po.RolePo;
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
public interface RoleService extends IService<RolePo> {

    List<RoleDto> roleList();
}
