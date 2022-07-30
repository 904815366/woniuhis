package com.woniu.web.controller;


import com.github.pagehelper.PageInfo;
import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.ArrangeDto;
import com.woniu.entity.dto.NullArrUserDto;
import com.woniu.entity.dto.UserDto;
import com.woniu.web.fo.*;
import com.woniu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
@Api( tags = {"用户管理"} )
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户列表分页(用名字模糊查询)
     * @param userList
     * @return
     */
    @RequestMapping("/list")
    @Operation( summary = "查询用户列表", description = "查询用户列表", tags = {"用户管理"} )
    public ResponseResult<PageInfo> userList(UserList userList){
        PageInfo<UserDto> pageInfo = userList.exec();
        return new ResponseResult<>(pageInfo,"OK",200);
    }

    @RequestMapping("/edit")
    @Operation( summary = "编辑用户信息", description = "编辑用户信息", tags = {"用户管理"} )
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

    @RequestMapping("/delete")
    @Operation( summary = "删除用户信息", description = "删除用户信息", tags = {"用户管理"} )
    public ResponseResult<Void> delUser(@RequestParam("id")Integer id){
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            userService.deleteById(id);
            responseResult = new ResponseResult<>(200,"ok");
        }catch (Exception e){
            responseResult = new ResponseResult<>(001,e.getMessage());
        }
        return responseResult;
    }

    @RequestMapping("/getByUsername")
    @Operation( summary = "根据用户名查询用户", description = "根据用户名查询用户", tags = {"用户管理"} )
    public ResponseResult<Void> queryUserByUsername(QueryUserByUsername queryUserByUsername){
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            queryUserByUsername.getUserByUsername();
            responseResult = new ResponseResult<>(200,"OK");
        }catch (Exception e){
            responseResult = new ResponseResult<>(002,e.getMessage());
        }
        return responseResult;
    }

    @RequestMapping("/addUser")
    @Operation( summary = "用户录入", description = "用户录入", tags = {"用户管理"} )
    public ResponseResult<Void> addUser(@RequestBody AddUser addUser){
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            addUser.addUser();
            responseResult = new ResponseResult<>(200,"OK");
        }catch (Exception e){
            responseResult = new ResponseResult<>(003,e.getMessage());
        }
        return responseResult;
    }

    @RequestMapping("/nullArrUserThis")
    @Operation( summary = "查询本周无安排的人员", description = "查询本周无安排的人员", tags = {"用户管理"} )
    public ResponseResult<List<NullArrUserDto>> nullArrUserListThis(NullArrUserList nullArrUserList){
        List<NullArrUserDto> thisList = nullArrUserList.getThisList();
        System.out.println(thisList);
        return new ResponseResult<>(thisList,"OK",200);
    }

    @RequestMapping("/nullArrUserNext")
    @Operation( summary = "查询下周无安排的人员", description = "查询下周无安排的人员", tags = {"用户管理"} )
    public ResponseResult<List<NullArrUserDto>> nullArrUserListNext(NullArrUserList nullArrUserList){
        List<NullArrUserDto> nextList = nullArrUserList.getNextList();
        System.out.println(nextList);
        return new ResponseResult<>(nextList,"OK",200);
    }
}

