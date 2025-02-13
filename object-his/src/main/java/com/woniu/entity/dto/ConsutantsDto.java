package com.woniu.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

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
@Schema(description = "会诊表")
@RedisHash("consutants")
public class ConsutantsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Id
    @Schema(name = "id", description = "会诊ID")
    private Integer id;

    @Schema(name = "patientname", description = "患者名称")
    private String patientname;

    @Schema(name = "registerid", description = "住院号")
    private Integer registerid;

    @Schema(name = "consutantstype", description = "会诊紧急情况（紧急，24小时，一般）")
    private String consutantstype;

    @Schema(name = "goal", description = "会诊目的")
    private String goal;

    @Schema(name = "patientcondition", description = "患者情况")
    private String patientcondition;

    @Schema(name = "consutantscondition", description = "诊疗情况")
    private String consutantscondition;

    @Schema(name = "status", description = "状态(未会诊/会诊中/已会诊)")
    private String status;

    @Schema(name = "userid", description = "会诊医生ID")
    private Integer userid;

    @Schema(name = "opinion", description = "回复会诊")
    private String opinion;

    @Schema(name = "replyid", description = "回复医生")
    private Integer replyid;

    @Schema(name = "applydeptid", description = "申请科室")
    private Integer applydeptid;

    @Schema(name = "replydeptid", description = "回复科室")
    private Integer replydeptid;

    private String familyname;



}
