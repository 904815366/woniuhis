package com.woniu.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.format.annotation.DateTimeFormat;

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
@TableName("drug")
public class DrugPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String type;

    private Double price;

    private Integer num;

    private String producer;

    private String producing;

    private String mdfunction;

    private String memo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date protime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validtime;

    private Integer alarmnum;

    private String batch;

}
