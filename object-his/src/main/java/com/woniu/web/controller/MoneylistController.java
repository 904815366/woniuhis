package com.woniu.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.eventbus.EventBus;
import com.woniu.config.ResponseResult;
import com.woniu.entity.converter.MoneylistConcerter;
import com.woniu.entity.dto.MoneylistDto;
import com.woniu.entity.po.MoneylistPo;
import com.woniu.service.MoneylistService;
import com.woniu.web.fo.ModifyStatusAndRegisterMoneyComment;
import com.woniu.web.fo.ModifyStatusAndRegisterMoneysComment;
import com.woniu.web.fo.MoneyListByRegisterIdAndStatusQuery;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RequestMapping("/moneylist")
@Api( tags = {"消费详情管理"} )
public class MoneylistController {

    @Autowired
    private MoneylistService  moneylistService;
    @Resource
    private MoneylistConcerter  moneylistConcerter;
    @Resource
    private EventBus bus;

//    查询所有的费用清单
    @GetMapping("/getMoneylists")
    @Operation( summary = "查询所有的费用清单", description = "查询所有的费用清单", tags = {"消费详情管理"} )
    public List<MoneylistDto> gotoMoneylist(Integer mid){
//        创建条件对象
        QueryWrapper<MoneylistPo> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("registerid",mid);

//调用service自带的条件查询方法
        List<MoneylistPo> list = moneylistService.list(objectQueryWrapper);

//        将查询出来的po转换为dto
        List<MoneylistDto> dtos = moneylistConcerter.from(list);
        return dtos;
    }


    @GetMapping("query/registerIdAndStatus")
    @Operation( summary = "根据住院号和状态查询消费列表", description = "根据住院号和状态查询消费列表", tags = {"消费详情管理"} )
    public ResponseResult<List<MoneylistDto>> queryMoneyListByRegisterIdAndStatus(
            MoneyListByRegisterIdAndStatusQuery moneyListByRegisterIdAndStatusQuery){
        return moneyListByRegisterIdAndStatusQuery.exec();

    }

    /**
     * 日结列表,结算单个消费订单
     * @param modifyStatusAndRegisterMoneyComment
     * @return
     */
    @PostMapping("/modifyStatusAndRegisterMoney")
    @Operation( summary = "日结列表,结算单个消费订单", description = "修改消费详情状态为已结算,并且根据住院号减去账户的余额", tags = {"消费详情管理"} )
    public ResponseResult<Void> modifyStatusAndRegisterMoney(
            @RequestBody ModifyStatusAndRegisterMoneyComment modifyStatusAndRegisterMoneyComment){
        bus.post(modifyStatusAndRegisterMoneyComment);
        return new ResponseResult<>(2000,"ok");
    }


    /**
     * 日结列表,批量结算消费订单
     * @param modifyStatusAndRegistersMoneyComment
     * @return
     */
    @PostMapping("/modifyStatusAndRegisterMoneys")
    @Operation( summary = "日结列表,批量结算消费订单", description = "批量修改消费详情状态为已结算,并且根据住院号减去账户的余额", tags = {"消费详情管理"} )
    public ResponseResult<Void> modifyStatusAndRegisterMoneys(
            @RequestBody ModifyStatusAndRegisterMoneysComment modifyStatusAndRegistersMoneyComment){
        bus.post(modifyStatusAndRegistersMoneyComment);
        return new ResponseResult<>(2000,"ok");
    }



}

