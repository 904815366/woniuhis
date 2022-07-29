package com.woniu.web.controller;


import com.google.common.eventbus.EventBus;
import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.MoneyrecordDto;
import com.woniu.entity.dto.RegisterDto;
import com.woniu.web.anon.IdempotentToken;
import com.woniu.web.fo.InsertMoneyRecordComment;
import com.woniu.web.fo.MoneyRecordListByIdQuery;
import com.woniu.web.fo.RegisterByIdQuery;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@RequestMapping("/moneyrecord")
public class MoneyrecordController {

    @Resource
    private EventBus bus;


    /**
     * 罗虎
     * 根据ID查询缴费列表
     * @return
     */
    @GetMapping("/ListById/{id}")
    public ResponseResult<List<MoneyrecordDto>> queryByIdRegister(@PathVariable("id") Integer id ){
        return new MoneyRecordListByIdQuery(id).exec();
    }


    /**
     * 罗虎
     * 添加缴费信息
     * @return
     */
    @PostMapping("/insertOne")
    @IdempotentToken
    public ResponseResult<Void> addMoneyrecord(@RequestBody InsertMoneyRecordComment insertMoneyRecordComment){
        bus.post(insertMoneyRecordComment);
        return new ResponseResult<>(2000,"ok");
    }


}

