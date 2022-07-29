package com.woniu.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "患者医嘱")
public class WarnDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(name = "id", description = "医嘱")
    private Integer id;

    @Schema(name = "patientid", description = "患者id")
    private String patientid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Schema(name = "warntime", description = "开立时间")
    private Date warntime;

    @Schema(name = "doctorname", description = "开立医生")
    private String doctorname;

    @Schema(name = "status", description = "医嘱状态")
    private Integer status;

    @Schema(name = "drugname", description = "药品名称")
    private String drugname;

    @Schema(name = "drugid", description = "药品id")
    private Integer drugid;

    @Schema(name = "num", description = "数量")
    private Integer num;

    @Schema(name = "measure", description = "单次剂量")
    private String measure;

    @Schema(name = "directions", description = "用法用量")
    private String directions;

    @Schema(name = "registerid", description = "住院id")
    private String registerid;

    @Schema(name = "warndeteilsid", description = "详情id")
    private String warndeteilsid;

    @Schema(name = "doctorid", description = "医生id")
    private String doctorid;

    @Schema(name = "nurseid", description = "护士id")
    private String nurseid;

    @Schema(name = "familyid", description = "科室id")
    private Integer familyid;
}
