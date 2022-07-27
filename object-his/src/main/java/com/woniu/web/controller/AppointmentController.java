package com.woniu.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.AppointmentDto;
import com.woniu.entity.po.AppointmentPo;
import com.woniu.mapper.mysql.AppointmentMysqlDao;
import com.woniu.web.fo.AppointmentlistByStutas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
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
@RequestMapping("/appointment")
public class AppointmentController {

    @Resource
    private AppointmentMysqlDao appointmentMysqlDao;

    @GetMapping("/list/status")
    public ResponseResult<List<AppointmentDto>> appointmentlistByStutas(AppointmentlistByStutas appointmentlistByStutas){
       return appointmentlistByStutas.exec();
    }
}

