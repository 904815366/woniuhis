package com.woniu.service;

import com.woniu.entity.po.UserPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.web.fo.UserModify;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
public interface UserService extends IService<UserPo> {
    Integer modifyById(UserPo userModify);

    Integer deleteById(Integer id) throws Exception;
}
