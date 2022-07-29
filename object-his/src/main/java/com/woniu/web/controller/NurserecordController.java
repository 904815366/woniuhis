package com.woniu.web.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.NurserecordDto;
import com.woniu.entity.dto.NursetypeDto;
import com.woniu.entity.po.NurserecordPo;
import com.woniu.mapper.mysql.NursetypeMysqlDao;
import com.woniu.repository.NurserecordRepository;
import com.woniu.service.NurserecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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

    @Autowired
    private NurserecordService nurserecordService;

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

    /**
     * xk添加护理记录表
     */
    @PostMapping("/addNureser")
    public ResponseResult addNurserecord(@RequestBody NurserecordPo nurserecordPo){
        nurserecordPo.setStatus("0");
        boolean save = nurserecordService.save(nurserecordPo);
        if (save){
            return new ResponseResult(200, "OK");
        }else {
            return new ResponseResult(400, "ERRO");
        }
    }

    /**
     * xk
     * 根据Id删除护理记录
     */
    @GetMapping("/delNur")
    public ResponseResult dltNurserBYId(Integer id){
        boolean delt = nurserecordService.removeById(id);
        if (delt){
            return new ResponseResult(200, "删除成功");
        }else {
            return  new ResponseResult(400, "ERRO");
        }
    }


}

