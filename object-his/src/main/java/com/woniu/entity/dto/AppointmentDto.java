package com.woniu.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

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
@Schema(description = "预约住院表")
@RedisHash("appointment")
public class AppointmentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "预约住院ID")
    private Integer id;

    @Schema(name = "patientid", description = "病人ID")
    private Integer patientid;

    @Schema(name = "name", description = "姓名")
    private String name;

    @Schema(name = "sex", description = "性别")
    private String sex;

    @Schema(name = "age", description = "年龄")
    private Integer age;

    @Schema(name = "phone", description = "联系方式")
    private String phone;

    @Schema(name = "card", description = "身份证")
    private String card;

    @Schema(name = "pathogeny", description = "病因")
    private String pathogeny;

    @Schema(name = "entertime", description = "入院时间")
    private LocalDateTime entertime;

    @Schema(name = "familyid", description = "科室ID")
    private Integer familyid;

    @Schema(name = "regtype", description = "挂号类型(专家/普通)")
    private String regtype;

    @Schema(name = "userid", description = "医生ID")
    private Integer userid;

    @Schema(name = "regprice", description = "挂号费")
    private Double regprice;

    @Schema(name = "status", description = "审核状态")
    private String status;



}
