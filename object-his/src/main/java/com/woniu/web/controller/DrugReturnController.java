package com.woniu.web.controller;

import com.github.pagehelper.PageInfo;
import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.DrugReturnDto;
import com.woniu.entity.dto.WarnDto;
import com.woniu.service.DrugReturnService;
import com.woniu.service.DrugrecordService;
import com.woniu.web.fo.DrugOutList;
import com.woniu.web.fo.DrugReturnInfo;
import com.woniu.web.fo.DrugReturnList;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/drugreturn")
@Api( tags = {"退药管理"} )
public class DrugReturnController {
    @Autowired
    private DrugReturnService drugReturnService;
    @Autowired
    private DrugrecordService drugrecordService;
    @GetMapping("/edit")
    @Operation( summary = "退药", description = "提交退药申请,更改医嘱详情", tags = {"发药管理"} )
    public ResponseResult<Void> returnAdd(Integer id,Integer returnnum){
        try {
            drugReturnService.returnAdd(id,returnnum);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(null, "Internal Server Error", 500);
        }
        return new ResponseResult<>(null, "SUCCESS", 200);
    }

    @GetMapping("/returnlist")
    @Operation( summary = "退药列表", description = "带分页查询", tags = {"发药管理"} )
    public ResponseResult<PageInfo<DrugReturnDto>> DetailList(DrugReturnInfo drugReturnInfo){
        try {
            System.out.println("到达查询退药详情");
            PageInfo<DrugReturnDto> returnInfo = drugReturnInfo.getReturnInfo();
            return new ResponseResult<>(returnInfo,"OK",200);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/queryreturnlist")
    @Operation( summary = "退药列表", description = "不带分页查询", tags = {"发药管理"} )
    public ResponseResult<List<DrugReturnDto>> DetailList(DrugReturnList drugReturnList){
        try {
            System.out.println("到达查询退药详情");
            List<DrugReturnDto> returnList = drugReturnList.getReturnList();
            return new ResponseResult<>(returnList,"OK",200);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/handlereturn")
    @Operation( summary = "退药", description = "删除申请记录,添加退药记录,添加费用清单", tags = {"退药管理"} )
    public ResponseResult<Void> handleReturn(String ids,Integer userid){
        try {
            String[] idArr = ids.split(",");
            drugrecordService.addDrugReturnrecord(idArr,userid);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(null, "Internal Server Error", 500);
        }
        return new ResponseResult<>(null, "SUCCESS", 200);
    }

    @GetMapping("/handlerefuse")
    @Operation( summary = "驳回退药", description = "删除申请记录", tags = {"退药管理"} )
    public ResponseResult<Void> handleRefuse(String ids){
        try {
            String[] idArr = ids.split(",");
            drugrecordService.addDrugRefuserecord(idArr);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(null, "Internal Server Error", 500);
        }
        return new ResponseResult<>(null, "SUCCESS", 200);
    }

    @GetMapping("/list")
    @Operation( summary = "申请退药列表", description = "条件查询(关联查询医嘱)", tags = {"退药管理"} )
    public ResponseResult<PageInfo> drugReturnList(DrugOutList drugOutList){
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
}
