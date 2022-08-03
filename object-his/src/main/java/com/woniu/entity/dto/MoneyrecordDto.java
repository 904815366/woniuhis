package com.woniu.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@Schema(description = "缴费表")
public class MoneyrecordDto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(name = "id", description = "缴费表ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(name = "registerid", description = "住院号")
    private Integer registerid;

    @Schema(name = "prepaymoney", description = "缴费金额")
    private Double prepaymoney;

    @Schema(name = "prepaytime", description = "缴费时间")
    private LocalDateTime prepaytime;

    @Schema(name = "userid", description = "收费人员ID")
    private Integer userid;

    @Schema(name = "type", description = "缴费方式")
    private String type;


}
