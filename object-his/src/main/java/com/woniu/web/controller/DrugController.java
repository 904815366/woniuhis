package com.woniu.web.controller;


import com.github.pagehelper.PageInfo;
import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.DrugDto;
import com.woniu.entity.dto.PatientDto;
import com.woniu.service.DrugService;
import com.woniu.web.fo.DrugList;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/drug")
@Api( tags = {"药房管理"} )
public class DrugController {
    @Autowired
    private DrugService drugService;

    @GetMapping("/list")
    @Operation( summary = "查询药品", description = "条件查询", tags = {"患者管理"} )
    public ResponseResult<PageInfo> getDrugList(DrugList drugList){
        try {
            System.out.println("到达druglist");
            PageInfo<DrugDto> pageInfo = drugList.exac();
            return new ResponseResult<>(pageInfo,"OK",200);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}

