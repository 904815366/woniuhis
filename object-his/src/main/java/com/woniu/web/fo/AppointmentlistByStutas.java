package com.woniu.web.fo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.config.ResponseResult;
import com.woniu.entity.converter.AppointmentConverter;
import com.woniu.entity.converter.RegisterConverter;
import com.woniu.entity.dto.AppointmentDto;
import com.woniu.entity.po.AppointmentPo;
import com.woniu.mapper.mysql.AppointmentMysqlDao;
import com.woniu.mapper.mysql.RegisterMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import lombok.Data;
import org.springframework.context.ApplicationContext;

import java.util.List;

@Data
public class AppointmentlistByStutas {

    private String status;

    public ResponseResult<List<AppointmentDto>> exec(){
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        AppointmentConverter appointmentConverter = applicationContext.getBean(AppointmentConverter.class);
        AppointmentMysqlDao appointmentMysqlDao = applicationContext.getBean(AppointmentMysqlDao.class);
        QueryWrapper<AppointmentPo> wrapper = new QueryWrapper<>();
        wrapper.eq("status", status);
        List<AppointmentPo> pos = appointmentMysqlDao.selectList(wrapper);
        List<AppointmentDto> dtos = appointmentConverter.from(pos);
        return new ResponseResult<>(dtos,"ok",2000);
    }
}
