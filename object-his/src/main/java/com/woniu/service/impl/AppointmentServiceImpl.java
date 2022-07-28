package com.woniu.service.impl;

import com.woniu.entity.po.AppointmentPo;
import com.woniu.mapper.mysql.AppointmentMysqlDao;
import com.woniu.repository.AppointmentRepository;
import com.woniu.service.AppointmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
@Service
@Transactional
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMysqlDao, AppointmentPo> implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Override
    public void createAppointment(AppointmentPo appointmentPo) {
        appointmentRepository.createAppointment(appointmentPo);
    }

    @Override
    public void removeAppointment(Integer id) {
        appointmentRepository.removeAppointment(id);
    }
}
