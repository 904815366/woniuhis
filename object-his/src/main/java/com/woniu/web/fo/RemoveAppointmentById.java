package com.woniu.web.fo;

import com.woniu.service.AppointmentService;
import com.woniu.util.ApplicationContextHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoveAppointmentById {
    private Integer id;
    public void removeAppointment(Integer id){
        AppointmentService bean = ApplicationContextHolder.getApplicationContext().getBean(AppointmentService.class);
        bean.removeAppointment(id);
    }
}
