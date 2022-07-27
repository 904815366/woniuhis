package com.woniu.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
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
@RedisHash("drug")
@Schema(description = "药品库存表")
public class DrugDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "药品ID")
    private Integer id;

    @Schema(name = "name", description = "药品名称")
    private String name;

    @Schema(name = "type", description = "药品类型")
    private String type;

    @Schema(name = "price", description = "药品价格")
    private Double price;

    @Schema(name = "num", description = "药品数量")
    private Integer num;

    @Schema(name = "producer", description = "生产商")
    private String producer;

    @Schema(name = "producing", description = "生产地")
    private String producing;

    @Schema(name = "mdfunction", description = "功效")
    private String mdfunction;

    @Schema(name = "memo", description = "备注")
    private String memo;

    @Schema(name = "protime", description = "生产时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date protime;

    @Schema(name = "validtime", description = "过期时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date validtime;

    @Schema(name = "batch", description = "批次")
    private String batch;

    @Schema(name = "alarmnum", description = "报警库存")
    private Integer alarmnum;

}
