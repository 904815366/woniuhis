package com.woniu.web.fo;

import com.woniu.config.ResponseResult;
import com.woniu.entity.po.RegisterPo;
import com.woniu.service.RegisterService;
import com.woniu.util.ApplicationContextHolder;
import lombok.Data;
import org.springframework.context.ApplicationContext;

@Data
public class ModifyRegister {
    private Integer id;

    private Integer patientid;

    private String name;

    private String sex;

    private Integer age;

    private String phone;

    private String card;

    private String area;

    private String diagnose;

    private Double money;

    private Integer chargeid;

    private String status;


    public ResponseResult<Void> exec(){
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        RegisterService registerService = applicationContext.getBean(RegisterService.class);
        RegisterPo po = RegisterPo.builder()
                .id(id).patientid(patientid).name(name).sex(sex).age(age).phone(phone)
                .card(card).area(area).diagnose(diagnose).money(money).chargeid(chargeid).status(status)
                .build();
        registerService.modifyRegister(po);
        return ResponseResult.ok();
    }
}
