package com.woniu.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Schema(description = "入院登记表")
public class RegisterDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "住院号")
    private Integer id;

    @Schema(name = "patientid", description = "病人账户ID")
    private Integer patientid;

    @Schema(name = "name", description = "姓名")
    private String name;

    @Schema(name = "sex", description = "性别")
    private String sex;

    @Schema(name = "age", description = "年龄")
    private Integer age;

    @Schema(name = "phone", description = "联系方式")
    private String phone;

    @Schema(name = "card", description = "身份证号码")
    private String card;

    @Schema(name = "area", description = "住址")
    private String area;

    @Schema(name = "diagnose", description = "门诊诊断")
    private String diagnose;

    @Schema(name = "familyid", description = "科室ID")
    private Integer familyid;

    @Schema(name = "bedid", description = "床位ID")
    private Integer bedid;

    @Schema(name = "money", description = "余额")
    private Double money;

    @Schema(name = "doctorid", description = "医生ID")
    private Integer doctorid;

    @Schema(name = "chargeid", description = "入院登记员ID")
    private Integer chargeid;

    @Schema(name = "entertime", description = "入院时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime entertime;

    @Schema(name = "outtime", description = "出院时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime outtime;

    @Schema(name = "status", description = "状态")
    private String status;

    @Schema(name = "wardid", description = "病房ID")
    private Integer wardid;

    @Schema(name = "used", description = "已用金额")
    private Double used;

    @Schema(name = "refund", description = "退款")
    private Double refund;

}
