package com.woniu.web.fo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.woniu.entity.po.WarndetailsPo;
import com.woniu.service.WarnService;
import com.woniu.util.ApplicationContextHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyCpoe {
    private Integer id;

    private Integer patientid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date warntime;

    private String doctorname;

    private Integer status;

    private Integer drugid;

    private Integer num;

    private String measure;

    private String directions;

    public void modifyCpoe(ModifyCpoe modifyCpoe){
        WarnService warnService = ApplicationContextHolder
                .getApplicationContext().getBean(WarnService.class);
        WarndetailsPo warndetailsPo = new WarndetailsPo();
        warndetailsPo.setDrugid(modifyCpoe.drugid);
        warndetailsPo.setWarnid(modifyCpoe.id);
        warndetailsPo.setNum(modifyCpoe.num);
        warndetailsPo.setDirections(modifyCpoe.directions);
        warndetailsPo.setMeasure(modifyCpoe.measure);
        warnService.updateWarn(modifyCpoe.patientid,modifyCpoe.warntime,warndetailsPo);
    }
}
