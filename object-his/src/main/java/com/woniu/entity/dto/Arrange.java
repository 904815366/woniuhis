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
@Schema(description = "排班表")
@RedisHash("arrange")
public class Arrange implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Id
    @Schema(name = "id", description = "排班ID")
    private Integer id;

    @Schema(name = "dutyuserid", description = "值班人员ID")
    private Integer dutyuserid;

    @Schema(name = "dutytime", description = "值班时间")
    private Integer dutytime;

    @Schema(name = "createtime", description = "排版时间")
    private LocalDateTime createtime;

    @Schema(name = "createuserid", description = "人事管理员ID")
    private Integer createuserid;


}
