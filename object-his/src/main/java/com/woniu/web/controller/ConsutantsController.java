package com.woniu.web.controller;


import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.ConsutantsDto;
import com.woniu.web.fo.QueryConsutatsListByApplydeptid;
import com.woniu.web.fo.QueryConsutatsListByReplydeptid;
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
@RequestMapping("/consutants")
public class ConsutantsController {

    @GetMapping("/getByApplydeptidList")
    public ResponseResult<List<ConsutantsDto>> getByApplydeptid(Integer deptId){
        try {
            QueryConsutatsListByApplydeptid queryConsutatsListByDepetId = new QueryConsutatsListByApplydeptid();
            List<ConsutantsDto> dtoList = queryConsutatsListByDepetId.consultationByApplydeptid(deptId);
            return new ResponseResult<>(dtoList,"SUCCESS", 200);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(null, "Internal Server Error", 500);
        }
    }

    @GetMapping("/getByReplydeptid")
    public ResponseResult<List<ConsutantsDto>> getByReplydeptid(Integer deptId){
        try {
            QueryConsutatsListByReplydeptid queryConsutatsListByReplydeptid = new QueryConsutatsListByReplydeptid();
            List<ConsutantsDto> dtoList = queryConsutatsListByReplydeptid.consultationByReplydeptid(deptId);
            return new ResponseResult<>(dtoList,"SUCCESS", 200);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(null, "Internal Server Error", 500);
        }
    }
}

