package com.woniu.web.fo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.woniu.entity.po.WarndetailsPo;
import com.woniu.service.WarnService;
import com.woniu.util.ApplicationContextHolder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyCpoe {
    @Schema(name = "id",description = "医嘱id")
    private Integer id;

    @Schema(name = "patientid",description = "患者id")
    private Integer patientid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Schema(name = "warntime",description = "医嘱开立时间")
    private Date warntime;

    @Schema(name = "doctorname",description = "医生名字")
    private String doctorname;

    @Schema(name = "status",description = "医嘱状态")
    private Integer status;

    @Schema(name = "drugid",description = "药品id")
    private Integer drugid;

    @Schema(name = "num",description = "药品数量")
    private Integer num;

    @Schema(name = "measure",description = "药品规格")
    private String measure;

    @Schema(name = "directions",description = "药品用法")
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
