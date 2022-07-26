package com.woniu.web.controller;


import com.woniu.config.ResponseResult;
import com.woniu.entity.converter.RoleConverter;
import com.woniu.entity.dto.RoleDto;
import com.woniu.entity.po.RolePo;
import com.woniu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
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
@Controller
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("/list")
    public ResponseResult<List<RoleDto>> roleList(){
        List<RoleDto> roleList = roleService.roleList();
        return new ResponseResult<>(roleList,"OK",200);
    }
}

