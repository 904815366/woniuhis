package com.woniu.web.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.PatientDto;
import com.woniu.web.fo.QueryPatientById;
import com.woniu.web.fo.QueryPatientList;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/patient")
@Api( tags = {"患者管理"} )
public class PatientController {
    @GetMapping("/list")
    @Operation( summary = "查询在院患者", description = "查询在院患者", tags = {"患者管理"} )
    public ResponseResult<PageInfo<PatientDto>> getPatientList(@RequestParam(name = "searchName",defaultValue = "")String name,
                                                           @RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
                                                           @RequestParam(name = "pageSize",defaultValue ="5")Integer pageSize){
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<PatientDto> patientList = new QueryPatientList().getPatientList(name);
            PageInfo<PatientDto> pageInfo = new PageInfo<>(patientList);
            return new ResponseResult<>(pageInfo,"SUCCESS",200);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(null,"Internal Server Error",500);
        }
    }

    @GetMapping("/getPatientById")
    @Operation( summary = "根据id查询患者", description = "根据id查询患者", tags = {"患者管理"} )
    public ResponseResult<PatientDto> getPatientById(Integer id){
        try {
            PatientDto patientById = new QueryPatientById().getPatientById(id);
            return new ResponseResult<>(patientById,"SUCCESS",200);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(null,"Internal Server Error",500);
        }
    }

}

