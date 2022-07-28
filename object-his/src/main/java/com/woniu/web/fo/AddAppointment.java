package com.woniu.web.fo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.woniu.entity.po.AppointmentPo;
import com.woniu.service.AppointmentService;
import com.woniu.util.ApplicationContextHolder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAppointment {
    @Schema(name = "name", description = "患者姓名")
    private String name;
    @Schema(name = "sex", description = "性别")
    private String sex;
    @Schema(name = "age", description = "年龄")
    private Integer age;
    @Schema(name = "phone", description = "联系方式")
    private String phone;
    @Schema(name = "card", description = "身份证号")
    private String card;
    @Schema(name = "diagnose", description = "入院诊断")
    private String diagnose;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
    private Date entertime;
    @Schema(name = "familyid", description = "入住科室id")
    private Integer familyid;

    public void addAppointment() {
        AppointmentPo appointmentPO = new AppointmentPo();
        appointmentPO.setName(name);
        appointmentPO.setSex(sex);
        appointmentPO.setAge(age);
        appointmentPO.setPhone(phone);
        appointmentPO.setCard(card);
        appointmentPO.setDiagnose(diagnose);
        appointmentPO.setEntertime(entertime);
        appointmentPO.setFamilyid(familyid);
        AppointmentService bean = ApplicationContextHolder.getApplicationContext().getBean(AppointmentService.class);
        bean.createAppointment(appointmentPO);
    }
}
