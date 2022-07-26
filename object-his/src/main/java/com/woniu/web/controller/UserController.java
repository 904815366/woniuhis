package com.woniu.web.controller;


import com.github.pagehelper.PageInfo;
import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.UserDto;
import com.woniu.web.fo.UserList;
import com.woniu.service.UserService;
import com.woniu.web.fo.UserModify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户列表分页(用名字模糊查询)
     * @param userList
     * @return
     */
    @RequestMapping("/list")
    public ResponseResult<PageInfo> userList(UserList userList){
        PageInfo<UserDto> pageInfo = userList.exec();
        return new ResponseResult<>(pageInfo,"OK",200);
    }

    @RequestMapping("/edit")
    public ResponseResult<Void> modifyUser(@RequestBody UserModify userModify){
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            userModify.userModify();
            responseResult = new ResponseResult<>(200,"ok");
        }catch (Exception e){
            responseResult = new ResponseResult<>(000,e.getMessage());
        }
        return responseResult;
    }

}

