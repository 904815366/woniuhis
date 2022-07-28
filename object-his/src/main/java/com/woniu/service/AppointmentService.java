package com.woniu.service;

import com.woniu.entity.po.AppointmentPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
public interface AppointmentService extends IService<AppointmentPo> {

    void createAppointment(AppointmentPo appointmentPo);

    void removeAppointment(Integer id);
}
