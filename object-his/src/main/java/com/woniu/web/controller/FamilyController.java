package com.woniu.web.controller;


import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.FamilyDto;
import com.woniu.service.FamilyService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/family")
@RestController
@Api( tags = {"科室管理"} )
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @RequestMapping("/list")
    @Operation( summary = "查询科室列表", description = "查询科室列表", tags = {"科室管理"} )
    public ResponseResult<List<FamilyDto>> fmlList(){
        List<FamilyDto> familyDtoList = familyService.fmlList();
        return new ResponseResult<>(familyDtoList,"OK",200);
    }
}

