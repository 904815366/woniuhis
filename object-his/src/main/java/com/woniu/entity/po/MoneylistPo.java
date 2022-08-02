package com.woniu.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
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
@TableName("moneylist")
@Builder
public class MoneylistPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer registerid;//住院号

    private BigDecimal consummoney;//消费金额

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime consumtime;//消费时间

    private String consumpart;//消费详情

    private String status;


    private String type;
}
