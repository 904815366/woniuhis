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
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
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
@Api( tags = {"护理记录管理"} )
public class NurserecordController {

    @Autowired
    private NurserecordRepository nurserecordRepository;

    @Autowired
    private NursetypeMysqlDao nursetypeMysqlDao;

    @Autowired
    private NurserecordService nurserecordService;

    @GetMapping("gotoNursers")//查看所有护理记录
    @Operation( summary = "查看所有护理记录", description = "查看所有护理记录", tags = {"护理记录管理"} )
    public PageInfo<NurserecordDto> getNurserecords(@RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize",defaultValue = "5") Integer pageSize,
                                                    @RequestParam(name = "rid")Integer rid){
        PageHelper.startPage(pageNum, pageSize);
        List<NurserecordDto> nursercordDtos = nurserecordRepository.getNursercordDtos(rid);
        return new PageInfo<>(nursercordDtos);
    }

    @GetMapping("/getNutypes")
    @Operation( summary = "查看所有护理类型供添加使用", description = "查看所有护理类型供添加使用", tags = {"护理记录管理"} )
    public List<NursetypeDto> getNusrtypes(){
        List<NursetypeDto> nursetypeDtos = nursetypeMysqlDao.queryNuresetype();
        return nursetypeDtos;
    }

    /**
     * xk添加护理记录表
     */
    @PostMapping("/addNureser")
    @Operation( summary = "添加护理记录", description = "添加护理记录", tags = {"护理记录管理"} )
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
     *
     */
    public void getRegist(){

    }
    /**
     * xk
     * 根据Id删除护理记录
     */
    @GetMapping("/delNur")
    @Operation( summary = "根据id删除护理记录", description = "根据id删除护理记录", tags = {"护理记录管理"} )
    public ResponseResult dltNurserBYId(Integer id){
        boolean delt = nurserecordService.removeById(id);
        if (delt){
            return new ResponseResult(200, "删除成功");
        }else {
            return  new ResponseResult(400, "ERRO");
        }
    }


}

