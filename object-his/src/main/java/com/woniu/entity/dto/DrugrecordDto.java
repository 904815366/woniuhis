package com.woniu.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Schema(description = "药品发放记录表")
public class DrugrecordDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "药品发放记录ID")
    private Integer id;

    @Schema(name = "drugid", description = "药品ID")
    private Integer drugid;

    @Schema(name = "num", description = "药品数量")
    private Integer num;

    @Schema(name = "type", description = "药品类型")
    private String type;

    @Schema(name = "time", description = "操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    @Schema(name = "userid", description = "用户id")
    private Integer userid;

    @Schema(name = "drugreturnid", description = "退药表ID")
    @TableField("drugreturnid")
    private Integer drugreturnid;

    @Schema(name = "warndetailsid", description = "医嘱详情ID")
    private Integer warndetailsid;

    @Schema(name = "name", description = "药品名")
    private String name;

    @Schema(name = "doctorid", description = "医生ID")
    private Integer doctorid;

    @Schema(name = "nurseid", description = "护士ID")
    private Integer nurseid;

    @Schema(name = "familyid", description = "科室ID")
    private Integer familyid;
}
