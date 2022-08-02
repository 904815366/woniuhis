package com.woniu.web.controller;


import com.github.pagehelper.PageInfo;
import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.DrugDto;
import com.woniu.entity.dto.DrugrecordDto;
import com.woniu.web.fo.DrugList;
import com.woniu.web.fo.DrugrecordList;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
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
@RestController
@RequestMapping("/drugrecord")
@Api( tags = {"药品记录查询"} )
public class DrugrecordController {
    @GetMapping("/list")
    @Operation( summary = "查询药品记录", description = "条件查询(带分页)", tags = {"发药记录查询"} )
    public ResponseResult<PageInfo> getDrugList(DrugrecordList drugrecordList){
        try {
            PageInfo<DrugrecordDto> pageInfo = drugrecordList.exac();
            return new ResponseResult<>(pageInfo,"OK",200);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

