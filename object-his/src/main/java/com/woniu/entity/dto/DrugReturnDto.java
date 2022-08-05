package com.woniu.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "退药表")
public class DrugReturnDto {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "科室ID")
    private Integer id;

    @Schema(name = "drugid", description = "药品ID")
    private Integer drugid;

    @Schema(name = "num", description = "数量")
    private Integer num;

    @Schema(name = "warnid", description = "医嘱表ID")
    private Integer warnid;

    @Schema(name = "applytime", description = "申请时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime applytime;

    @Schema(name = "doctorid", description = "医生ID")
    private Integer doctorid;

    @Schema(name = "nurseid", description = "护士ID")
    private Integer nurseid;

    @Schema(name = "familyid", description = "科室ID")
    private Integer familyid;

    @Schema(name = "registerid", description = "住院ID")
    private Integer registerid;

    @Schema(name = "patientname", description = "病人姓名")
    private String patientname;
}
