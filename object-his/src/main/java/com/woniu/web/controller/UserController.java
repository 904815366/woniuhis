package com.woniu.web.controller;


import com.github.pagehelper.PageInfo;
import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.ArrangeDto;
import com.woniu.entity.dto.NullArrUserDto;
import com.woniu.entity.dto.UserByFamilyDto;
import com.woniu.entity.dto.UserDto;
import com.woniu.entity.po.UserPo;
import com.woniu.util.MinioUtils;
import com.woniu.web.fo.*;
import com.woniu.repository.UserRepository;
import com.woniu.web.fo.AddUser;
import com.woniu.web.fo.QueryUserByUsername;
import com.woniu.web.fo.UserList;
import com.woniu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */

@RestController
@RequestMapping("/user")
@Api(tags = {"用户管理"})
public class UserController {
    @Autowired
    private UserService userService;

    @Resource
    private MinioUtils minioUtils;

    @RequestMapping("/userinfo")
    @Operation(summary = "根据id查询用户", description = "根据id查询用户", tags = {"用户管理"})
    public ResponseResult<UserDto> userinfo(@RequestParam("id") Integer id) {
        UserDto userById = userService.getUserById(id);
        return new ResponseResult<>(userById, "OK", 200);
    }
    @Autowired
    private UserRepository userRepository;

    /**
     * 用户列表分页(用名字模糊查询)
     *
     * @param userList
     * @return
     */
    @RequestMapping("/list")
    @Operation(summary = "查询用户列表", description = "查询用户列表", tags = {"用户管理"})
    public ResponseResult<PageInfo> userList(UserList userList) {
        PageInfo<UserDto> pageInfo = userList.exec();
        return new ResponseResult<>(pageInfo, "OK", 200);
    }

    @RequestMapping("/edit")
    @Operation(summary = "编辑用户信息", description = "编辑用户信息", tags = {"用户管理"})
    public ResponseResult<Void> modifyUser(@RequestBody UserModify userModify) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            userModify.userModify();
            responseResult = new ResponseResult<>(200, "ok");
        } catch (Exception e) {
            responseResult = new ResponseResult<>(000, e.getMessage());
        }
        return responseResult;
    }

    @RequestMapping("/delete")
    @Operation(summary = "删除用户信息", description = "删除用户信息", tags = {"用户管理"})
    public ResponseResult<Void> delUser(@RequestParam("id") Integer id) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            userService.deleteById(id);
            responseResult = new ResponseResult<>(200, "ok");
        } catch (Exception e) {
            responseResult = new ResponseResult<>(001, e.getMessage());
        }
        return responseResult;
    }

    @RequestMapping("/getByUsername")
    @Operation(summary = "根据用户名查询用户", description = "根据用户名查询用户", tags = {"用户管理"})
    public ResponseResult<Void> queryUserByUsername(QueryUserByUsername queryUserByUsername) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            queryUserByUsername.getUserByUsername();
            responseResult = new ResponseResult<>(200, "OK");
        } catch (Exception e) {
            responseResult = new ResponseResult<>(002, e.getMessage());
        }
        return responseResult;
    }

    @RequestMapping("/addUser")
    @Operation(summary = "用户录入", description = "用户录入", tags = {"用户管理"})
    public ResponseResult<Void> addUser(@RequestBody AddUser addUser) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            addUser.addUser();
            responseResult = new ResponseResult<>(200, "OK");
        } catch (Exception e) {
            responseResult = new ResponseResult<>(003, e.getMessage());
        }
        return responseResult;
    }

    @RequestMapping("/nullArrUser")
    @Operation( summary = "查询无安排的人员", description = "查询无安排的人员", tags = {"用户管理"} )
    public ResponseResult<List<NullArrUserDto>> nullArrUserListThis(NullArrUserList nullArrUserList){
        List<NullArrUserDto> thisList = nullArrUserList.getList();
        System.out.println(thisList);
        return new ResponseResult<>(thisList, "OK", 200);
    }
    
    @RequestMapping("/updSelf")
    @Operation(summary = "修改个人密码", description = "修改个人密码", tags = {"用户管理"})
    public ResponseResult<Void> updSelf(@RequestParam("id") Integer id, @RequestParam("password") String password) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            userService.updSelf(id, password);
            responseResult = new ResponseResult<>(200, "OK");
        } catch (Exception e) {
            responseResult = new ResponseResult<>(004, e.getMessage());
        }
        return responseResult;
    }

    @RequestMapping("/psw")
    @Operation(summary = "验证原密码", description = "验证原密码", tags = {"用户管理"})
    public ResponseResult<Void> psw(@RequestParam("oPsw") String oPsw, @RequestParam("password") String password) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        Boolean psw = userService.psw(oPsw, password);
        if (psw) {
            responseResult = new ResponseResult<>(200, "OK");
        } else {
            responseResult = new ResponseResult<>(005, "密码错误");
        }
        return responseResult;
    }


    @PostMapping("/uploadFile")
    @Operation(summary = "上传头像", description = "上传头像", tags = {"用户管理"})
    public ResponseResult<Void> uploadFile(@RequestPart("files") MultipartFile[] files, @RequestParam("registerid") Integer registerid) {
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            String contentType = null;
            if (".jpg".equals(filename.substring(filename.lastIndexOf(".")))) {
                contentType = "image/jpeg";
            } else {
                contentType = "image/png";
            }
            String uuid = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));

            userService.updateById(UserPo.builder().id(registerid).avatar(uuid).build());

            minioUtils.putObject("avatar", file, uuid, contentType);
//            String url = minioUtils.getObjectUrl("avatar", uuid, 7, TimeUnit.DAYS);
//            System.out.println(url);
        }
//        System.out.println(registerid);
        return ResponseResult.ok();
    }


    @GetMapping("/queryUserAvatar/{id}")
    @Operation(summary = "获取头像", description = "获取头像", tags = {"用户管理"})
    public ResponseResult<String> queryUserAvatar(@PathVariable("id") Integer id) {
        try {
            System.out.println(id);
            UserPo po = userService.getById(id);
            String avatar = po.getAvatar();
            if (avatar == null){
                return new ResponseResult<>(null, "erorr", 4000);
            }
            String url = minioUtils.getObjectUrl("avatar", avatar, 7, TimeUnit.DAYS);
            return new ResponseResult<>(url, "ok", 2000);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(null, "erorr", 4000);
        }
    }
    /**
     *xk
     * @return当前科室所有医生
     */
    @GetMapping("getUsers")
    @Operation( summary = "用户录入", description = "用户录入", tags = {"用户管理"} )
    public List<UserByFamilyDto> getUserByfals(Integer fid){
        List<UserByFamilyDto> dtos = userRepository.getUserByfls(fid);
        return dtos;
    }

}

