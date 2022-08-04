package com.woniu.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.AppointmentDto;
import com.woniu.entity.po.AppointmentPo;
import com.woniu.mapper.mysql.AppointmentMysqlDao;
import com.woniu.web.fo.AddAppointment;
import com.woniu.web.fo.AppointmentlistByStutas;
import com.woniu.web.fo.RemoveAppointmentById;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
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
@RequestMapping("/appointment")
@Api( tags = {"预约管理"} )
public class AppointmentController {

    @Resource
    private AppointmentMysqlDao appointmentMysqlDao;

    @GetMapping("/list/status")
    @Operation( summary = "根据状态查询预约列表", description = "根据状态查询预约列表", tags = {"预约管理"} )
    public ResponseResult<List<AppointmentDto>> appointmentlistByStutas(AppointmentlistByStutas appointmentlistByStutas){
       return appointmentlistByStutas.exec();
    }

    @GetMapping("/queryListByName")
    @Operation( summary = "模糊分页查询列表", description = "模糊分页查询列表", tags = {"预约管理"} )
    public ResponseResult<PageInfo<AppointmentDto>> queryAppByName(@RequestParam(name = "searchName",defaultValue = "")String name,
                                                               @RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
                                                               @RequestParam(name = "pageSize",defaultValue ="5")Integer pageSize){
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<AppointmentDto> appointmentListByName = appointmentMysqlDao.getAppointmentListByName(name);
            PageInfo<AppointmentDto> pageInfo = new PageInfo<>(appointmentListByName);
            return new ResponseResult<>(pageInfo,"SUCCESS", 200);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(null, "Internal Server Error", 500);
        }
    }

    @PostMapping("/addAppointment")
    @Operation( summary = "添加预约信息", description = "添加预约信息", tags = {"预约管理"} )
    public ResponseResult<Void> addAppointment(@RequestBody AddAppointment addAppointment){
        try {
            addAppointment.addAppointment();
            return new ResponseResult<>(null,"SUCCESS", 200);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(null, "Internal Server Error", 500);
        }
    }

    @GetMapping("/delAppointment")
    @Operation( summary = "根据ID删除预约信息", description = "根据ID删除预约信息", tags = {"预约管理"} )
    public ResponseResult<Void> delAppointment(Integer id){
        try {
            new RemoveAppointmentById().removeAppointment(id);
            return new ResponseResult<>(null,"SUCCESS", 200);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(null, "Internal Server Error", 500);
        }
    }



}

