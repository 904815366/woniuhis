package com.woniu.web.fo;

import com.woniu.entity.dto.AppointmentDto;
import com.woniu.mapper.mysql.AppointmentMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryAppointmentByName {
    private String name;

    /**
     * 根据姓名查询住院通知单
     * @param name
     * @return
     */
    public List<AppointmentDto> queryAppointmentByName(String name){
        AppointmentMysqlDao bean = ApplicationContextHolder.getApplicationContext().getBean(AppointmentMysqlDao.class);
        return bean.getAppointmentListByName(name);
    }
}
