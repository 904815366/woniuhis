package com.woniu.web.controller;


import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.MoneyrecordDto;
import com.woniu.entity.dto.RegisterDto;
import com.woniu.web.fo.MoneyRecordListByIdQuery;
import com.woniu.web.fo.RegisterByIdQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/moneyrecord")
public class MoneyrecordController {


    /**
     * 罗虎
     * 根据ID查询单个入院信息
     * @return
     */
    @GetMapping("/ListById/{id}")
    public ResponseResult<List<MoneyrecordDto>> queryByIdRegister(@PathVariable("id") Integer id ){
        return new MoneyRecordListByIdQuery(id).exec();

    }
}

