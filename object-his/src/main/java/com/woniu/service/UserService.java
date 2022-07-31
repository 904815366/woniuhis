package com.woniu.service;

import com.woniu.entity.dto.UserDto;
import com.woniu.entity.po.UserPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.web.fo.UserModify;
import org.apache.ibatis.annotations.Param;

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

    UserDto getUserByUsername(String searchUsername);

    Integer addUser(UserPo userPo);

    UserDto getUserById(Integer id);

    Integer updSelf(Integer id,String password) throws Exception;

    Boolean psw(String oPsw, String password);
}
