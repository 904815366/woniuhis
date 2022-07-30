package com.woniu.web.controller;

import com.github.pagehelper.PageInfo;
import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.RegisterDto;
import com.woniu.entity.dto.UserDto;
import com.woniu.entity.dto.WarnDto;
import com.woniu.entity.dto.WarndetailsDto;
import com.woniu.web.fo.DrugDetailList;
import com.woniu.web.fo.DrugOutList;
import com.woniu.web.fo.DrugOutUserList;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/drugout")
@Api( tags = {"发药管理"} )
public class DrugoutController {
    @GetMapping("/list")
    @Operation( summary = "发药列表", description = "条件查询(关联查询医嘱)", tags = {"发药管理"} )
    public ResponseResult<PageInfo> drugOutList(DrugOutList drugOutList){
        try {
            System.out.println("到达drugoutlist");
            PageInfo<WarnDto> pageInfo = drugOutList.exac();
            System.out.println(pageInfo);
            return new ResponseResult<>(pageInfo,"OK",200);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/registerlist")
    @Operation( summary = "住院患者列表", description = "不带分页查询(状态值为2)", tags = {"发药管理"} )
    public ResponseResult<List<RegisterDto>> RegisterList(DrugOutList drugOutList){
        try {
            List<RegisterDto> registerList = drugOutList.getRegisterList();
            System.out.println(registerList);
            return new ResponseResult<>(registerList,"OK",200);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("/userlist")
    @Operation( summary = "用户列表", description = "不带分页查询", tags = {"发药管理"} )
    public ResponseResult<List<UserDto>> UserList(DrugOutUserList drugOutUserList){
        try {
            List<UserDto> userDtoList = drugOutUserList.exec();
            return new ResponseResult<>(userDtoList,"OK",200);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/detaillist")
    @Operation( summary = "医嘱详情列表", description = "不带分页查询", tags = {"发药管理"} )
    public ResponseResult<List<WarndetailsDto>> DetailList(DrugDetailList drugDetailList){
        try {
            System.out.println("到达查询医嘱详情");
            List<WarndetailsDto> warndetailsDto = drugDetailList.getWarndetailsList();
            return new ResponseResult<>(warndetailsDto,"OK",200);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
