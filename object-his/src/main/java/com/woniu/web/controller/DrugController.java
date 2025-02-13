package com.woniu.web.controller;


import com.github.pagehelper.PageInfo;
import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.DrugDto;
import com.woniu.web.fo.DrugAdd;
import com.woniu.web.fo.DrugEdit;
import com.woniu.web.fo.DrugList;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;


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
@RequestMapping("/drug")
@Api( tags = {"药品查询"} )
public class DrugController {

    @GetMapping("/list")
    @Operation( summary = "查询药品", description = "条件查询(带分页)", tags = {"药品查询"} )
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

    @GetMapping("/druglist")
    @Operation( summary = "查询药品", description = "查询(不带分页)", tags = {"药品查询"} )
    public ResponseResult<List<DrugDto>> queryDrugList(DrugList drugList){
        try {
            System.out.println("到达druglist");
            List<DrugDto> list = drugList.getDrugList();
            return new ResponseResult<>(list,"OK",200);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/edit")
    @Operation( summary = "修改报警库存", description = "修改报警库存", tags = {"药品查询"} )
    public ResponseResult<String> DrugEdit(@RequestBody DrugEdit drugEdit){
        try {
            drugEdit.exac();
            return new ResponseResult<>("修改成功","OK",200);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/add")
    @Operation( summary = "添加药品", description = "如果存在该批次则增加数量", tags = {"药品查询"} )
    public ResponseResult<String> DrugAdd(@RequestBody DrugAdd drugAdd){
        try {
            drugAdd.exac();
            return new ResponseResult<>("添加成功","OK",200);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

