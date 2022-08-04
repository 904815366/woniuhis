package com.woniu.web.controller;


import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.ConsutantsDto;
import com.woniu.service.ConsutantsService;
import com.woniu.web.fo.*;
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
@RequestMapping("/consutants")
@Api(tags = {"会诊管理"})
public class ConsutantsController {

    @Autowired
    private ConsutantsService consutantsService;

    @GetMapping("/getByApplydeptidList")
    @Operation(summary = "查询会诊列表", description = "查询会诊列表", tags = {"会诊管理"})
    public ResponseResult<List<ConsutantsDto>> getByApplydeptid(Integer deptId) {
        QueryConsutatsListByApplydeptid queryConsutatsListByDepetId = new QueryConsutatsListByApplydeptid();
        List<ConsutantsDto> dtoList = queryConsutatsListByDepetId.consultationByApplydeptid(deptId);
        return new ResponseResult<>(dtoList, "SUCCESS", 200);
    }

    @GetMapping("/getByReplydeptid")
    @Operation(summary = "查询回复会诊列表", description = "查询回复会诊列表", tags = {"会诊管理"})
    public ResponseResult<List<ConsutantsDto>> getByReplydeptid(Integer deptId) {
        QueryConsutatsListByReplydeptid queryConsutatsListByReplydeptid = new QueryConsutatsListByReplydeptid();
        List<ConsutantsDto> dtoList = queryConsutatsListByReplydeptid.consultationByReplydeptid(deptId);
        return new ResponseResult<>(dtoList, "SUCCESS", 200);
    }

    @PostMapping("/addApplyConsultation")
    @Operation(summary = "发起会诊", description = "发起会诊", tags = {"会诊管理"})
    public ResponseResult<Void> addApplyConsultation(@RequestBody AddApplyConsultation addApplyConsultation) {
        System.out.println(addApplyConsultation);
        addApplyConsultation.AddApplyConsultation();
        return new ResponseResult<>(null, "SUCCESS", 200);
    }

    @PostMapping("/replyConsultation")
    @Operation(summary = "回复会诊", description = "回复会诊", tags = {"会诊管理"})
    public ResponseResult<Void> replyConsultation(@RequestBody ModifyReplyConsultation modifyReplyConsultation) {
        modifyReplyConsultation.replyConsultation();
        return new ResponseResult<>(null, "SUCCESS", 200);
    }

    @PostMapping("/modifyApplyConsultation")
    @Operation(summary = "修改会诊", description = "修改会诊", tags = {"会诊管理"})
    public ResponseResult<Void> modifyApplyConsultation(@RequestBody ModifyApplyConsultation modifyApplyConsultation) {

        modifyApplyConsultation.modifyApplyConsultation();
        return new ResponseResult<>(null, "SUCCESS", 200);
    }

    @GetMapping("/removeById")
    @Operation(summary = "删除会诊", description = "删除会诊", tags = {"会诊管理"})
    public ResponseResult<Void> removeById(Integer id) {
        consutantsService.removeById(id);
        return new ResponseResult<>(null, "SUCCESS", 200);
    }
}
