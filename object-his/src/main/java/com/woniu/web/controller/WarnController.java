package com.woniu.web.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.WarnDto;
import com.woniu.service.WarnService;
import com.woniu.web.fo.AddCpoeFo;
import com.woniu.web.fo.ModifyCpoe;
import com.woniu.web.fo.QueryWarnList;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
@RestController
@RequestMapping("/warn")
@Api( tags = {"医嘱管理"} )
public class WarnController {
    @Autowired
    private WarnService warnService;
    @GetMapping("/getWarnList")
    @Operation( summary = "查询患者医嘱", description = "查询患者医嘱", tags = {"医嘱管理"} )
    public ResponseResult<PageInfo<WarnDto>> getWarnListById(@RequestParam(name = "patientid") Integer patientid,
                                                             @RequestParam(name = "pageNum") Integer pageNum,
                                                             @RequestParam(name = "pageSize") Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            QueryWarnList queryWarnList = new QueryWarnList();
            List<WarnDto> wsrnList = queryWarnList.getWsrnList(patientid);
            PageInfo<WarnDto> pageInfo = new PageInfo<>(wsrnList);
            return new ResponseResult<>(pageInfo, "SUCCESS", 200);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(null, "Internal Server Error", 500);
        }
    }

    @PostMapping("/modifyCpoe")
    @Operation( summary = "修改患者医嘱", description = "修改患者医嘱", tags = {"医嘱管理"} )
    public ResponseResult<Void> modifyCpoe(@RequestBody ModifyCpoe cpoe) {
        try {
            System.out.println(cpoe);
            ModifyCpoe modifyCpoe = new ModifyCpoe();
            modifyCpoe.modifyCpoe(cpoe);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(null, "Internal Server Error", 500);
        }
        return new ResponseResult<>(null, "SUCCESS", 200);
    }

    @GetMapping("/delCpoeById")
    @Operation( summary = "删除医嘱", description = "删除医嘱", tags = {"医嘱管理"} )
    public ResponseResult<Void> removeCpoe(Integer id){
        try {
            warnService.deleteWarn(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(null, "Internal Server Error", 500);
        }
        return new ResponseResult<>(null, "SUCCESS", 200);
    }

    @GetMapping("/updateCpoeStatus")
    @Operation( summary = "更新医嘱状态", description = "更新医嘱状态", tags = {"医嘱管理"} )
    public ResponseResult<Void> updateCpoeStatus(String ids){
        try {
            String[] idArr = ids.split(",");
            warnService.updatestatus(idArr);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(null, "Internal Server Error", 500);
        }
        return new ResponseResult<>(null, "SUCCESS", 200);
    }

    @PostMapping("/addCpoe")
    @Operation( summary = "新增医嘱", description = "新增医嘱", tags = {"医嘱管理"} )
    public ResponseResult<Void> addCpoe(@RequestBody AddCpoeFo addCpoeFo){
        try {
            System.out.println(addCpoeFo);
            AddCpoeFo cpoeFo = new AddCpoeFo();
            cpoeFo.addCpoe(addCpoeFo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(null, "Internal Server Error", 500);
        }
        return new ResponseResult<>(null, "SUCCESS", 200);
    }
}

