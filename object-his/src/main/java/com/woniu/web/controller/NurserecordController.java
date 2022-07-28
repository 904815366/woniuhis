package com.woniu.web.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.entity.dto.NurserecordDto;
import com.woniu.entity.dto.NursetypeDto;
import com.woniu.mapper.mysql.NursetypeMysqlDao;
import com.woniu.repository.NurserecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
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
@RequestMapping("/nurserecord")
public class NurserecordController {

    @Autowired
    private NurserecordRepository nurserecordRepository;

    @Autowired
    private NursetypeMysqlDao nursetypeMysqlDao;

    @GetMapping("gotoNursers")//查看所有护理记录
    public PageInfo<NurserecordDto> getNurserecords(@RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize",defaultValue = "5") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<NurserecordDto> nursercordDtos = nurserecordRepository.getNursercordDtos();
        return new PageInfo<>(nursercordDtos);
    }

    @GetMapping("/getNutypes")
    public List<NursetypeDto> getNusrtypes(){
        List<NursetypeDto> nursetypeDtos = nursetypeMysqlDao.queryNuresetype();
        return nursetypeDtos;
    }

}

