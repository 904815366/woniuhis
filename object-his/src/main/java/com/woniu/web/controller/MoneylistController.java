package com.woniu.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.eventbus.EventBus;
import com.woniu.config.ResponseResult;
import com.woniu.entity.converter.MoneylistConcerter;
import com.woniu.entity.dto.MoneylistDto;
import com.woniu.entity.po.MoneylistPo;
import com.woniu.service.MoneylistService;
import com.woniu.web.fo.MoneyListByRegisterIdAndStatusQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class MoneylistController {

    @Autowired
    private MoneylistService  moneylistService;
    @Resource
    private MoneylistConcerter  moneylistConcerter;
    @Resource
    private EventBus bus;

//    查询所有的费用清单
    @GetMapping("/getMoneylists")
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
    public ResponseResult<List<MoneylistDto>> queryMoneyListByRegisterIdAndStatus(
            MoneyListByRegisterIdAndStatusQuery moneyListByRegisterIdAndStatusQuery){
        return moneyListByRegisterIdAndStatusQuery.exec();

    }



}

