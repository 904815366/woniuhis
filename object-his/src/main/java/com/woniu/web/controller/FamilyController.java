package com.woniu.web.controller;


import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.FamilyDto;
import com.woniu.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/family")
@RestController
public class FamilyController {
    @Autowired
    private FamilyService familyService;
    @RequestMapping("/list")
    public ResponseResult<List<FamilyDto>> fmlList(){
        List<FamilyDto> familyDtoList = familyService.fmlList();
        return new ResponseResult<>(familyDtoList,"OK",200);
    }
}

