package com.woniu.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Schema(description = "消费详情表")
public class MoneylistDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "消费详情ID")
    private Integer id;

    @Schema(name = "registerid", description = "住院号")
    private Integer registerid;//住院号

    @Schema(name = "consummoney", description = "消费金额")
    private BigDecimal consummoney;//消费金额

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Schema(name = "consumtime", description = "消费时间")
    private LocalDateTime consumtime;//消费时间

    @Schema(name = "consumpart", description = "消费详情")
    private String consumpart;//消费详情

    @Schema(name = "status", description = "状态")
    private String status;

    @Schema(name = "type", description = "付款方式")
    private String type;
}
