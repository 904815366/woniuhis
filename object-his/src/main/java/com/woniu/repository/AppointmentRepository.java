package com.woniu.repository;

import com.woniu.entity.po.AppointmentPo;
import com.woniu.mapper.mysql.AppointmentMysqlDao;
import com.woniu.mapper.mysql.WarnMysqlDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AppointmentRepository {
    //使用依赖注入生成全参构造方法
    private final AppointmentMysqlDao appointmentMysqlDao;

    public void createAppointment(AppointmentPo appointmentPo){
        appointmentMysqlDao.createAppointment(appointmentPo);
    }

    public void removeAppointment(Integer id){
        appointmentMysqlDao.removeAppointment(id);
    }
}
