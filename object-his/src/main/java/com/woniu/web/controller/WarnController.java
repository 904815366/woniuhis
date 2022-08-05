package com.woniu.web.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.WarnDto;
import com.woniu.repository.WarnRepository;
import com.woniu.service.WarnService;
import com.woniu.web.fo.AddCpoeFo;
import com.woniu.web.fo.ModifyCpoe;
import com.woniu.web.fo.QueryWarnList;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
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
@Api(tags = {"医嘱管理"})
public class WarnController {
    @Autowired
    private WarnService warnService;

    @Autowired
    private WarnRepository repository;

    @GetMapping("/getWarnList")
    @Operation(summary = "查询患者医嘱", description = "查询患者医嘱", tags = {"医嘱管理"})
    public ResponseResult<PageInfo<WarnDto>> getWarnListById(@RequestParam(name = "patientid") Integer patientid,
                                                             @RequestParam(name = "pageNum") Integer pageNum,
                                                             @RequestParam(name = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWarnList queryWarnList = new QueryWarnList();
        List<WarnDto> wsrnList = queryWarnList.getWsrnList(patientid);
        PageInfo<WarnDto> pageInfo = new PageInfo<>(wsrnList);
        return new ResponseResult<>(pageInfo, "SUCCESS", 200);
    }

    @PostMapping("/modifyCpoe")
    @Operation(summary = "修改患者医嘱", description = "修改患者医嘱", tags = {"医嘱管理"})
    public ResponseResult<Void> modifyCpoe(@RequestBody ModifyCpoe cpoe) {
        ModifyCpoe modifyCpoe = new ModifyCpoe();
        modifyCpoe.modifyCpoe(cpoe);
        return new ResponseResult<>(null, "SUCCESS", 200);
    }

    @GetMapping("/delCpoeById")
    @Operation(summary = "删除医嘱", description = "删除医嘱", tags = {"医嘱管理"})
    public ResponseResult<Void> removeCpoe(Integer id) {
        warnService.deleteWarn(id);
        return new ResponseResult<>(null, "SUCCESS", 200);
    }

    @GetMapping("/updateCpoeStatus")
    @Operation(summary = "更新医嘱状态", description = "更新医嘱状态", tags = {"医嘱管理"})
    public ResponseResult<Void> updateCpoeStatus(String ids, Integer status,Integer nurseid) {
        String[] idArr = ids.split(",");
        warnService.updatestatus(idArr, status,nurseid);
        return new ResponseResult<>(null, "SUCCESS", 200);
    }

    @PostMapping("/addCpoe")
    @Operation(summary = "新增医嘱", description = "新增医嘱", tags = {"医嘱管理"})
    public ResponseResult<Void> addCpoe(@RequestBody AddCpoeFo addCpoeFo) {
        AddCpoeFo cpoeFo = new AddCpoeFo();
        cpoeFo.addCpoe(addCpoeFo);
        return new ResponseResult<>(null, "SUCCESS", 200);
    }

    /**
     * 通过住院编号查询患者医嘱详情 xk
     */
    @GetMapping("/getWarnsByRid/{rid}/{status}")
    @Operation(summary = "通过住院号码查询患者医嘱详情",description = "通过住院号码查询患者医嘱详情",
               tags = "医嘱管理")
    public PageInfo<WarnDto> getWarnsByRegisterid(@RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum,
                                                  @RequestParam(name = "pageSize",defaultValue = "5") Integer pageSize,
                                                  @PathVariable("rid") Integer registerid,
                                                  @PathVariable("status") String status){
        PageHelper.startPage(pageNum, pageSize);
        List<WarnDto> dtos = repository.getWarnsByRegisterid(registerid, status);
        PageInfo<WarnDto> pageInfo = new PageInfo<>(dtos);
        return pageInfo;
    }
}

