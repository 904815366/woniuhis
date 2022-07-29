package com.woniu.web.fo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.woniu.service.WarnService;
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
public class AddCpoeFo {

    @Schema(name = "patientname", description = "患者姓名")
    private String patientname;

    @Schema(name = "patientid", description = "患者id")
    private Integer patientid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone = "GMT+8")
    @Schema(name = "warntime", description = "开立时间")
    private Date warntime;

    @Schema(name = "doctorid", description = "开立医生")
    private Integer doctorid;

    @Schema(name = "drugid", description = "药品id")
    private Integer drugid;

    @Schema(name = "num", description = "数量")
    private Integer num;

    @Schema(name = "measure", description = "单次剂量")
    private String measure;

    @Schema(name = "directions", description = "用法用量")
    private String directions;

    @Schema(name = "familyid",description = "开立科室id")
    private Integer familyid;

    public void addCpoe(AddCpoeFo addCpoeFo){
        WarnService bean = ApplicationContextHolder.getApplicationContext().getBean(WarnService.class);
        bean.addCpoe(addCpoeFo);
    }
}
