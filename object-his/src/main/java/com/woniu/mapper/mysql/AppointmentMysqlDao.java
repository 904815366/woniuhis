package com.woniu.mapper.mysql;

import com.woniu.entity.dto.AppointmentDto;
import com.woniu.entity.po.AppointmentPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
@Mapper
public interface AppointmentMysqlDao extends BaseMapper<AppointmentPo> {
    /**
     * 查询住院通知单
     * @param name
     * @return
     */
    List<AppointmentDto> getAppointmentListByName(String name);

    /**
     * 开具住院通知单
     * @param appointmentPo
     */
    void createAppointment(AppointmentPo appointmentPo);

    void removeAppointment(Integer id);
}
