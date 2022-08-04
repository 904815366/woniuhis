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
@Schema(description = "护理记录表")
public class NurserecordDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "id")
    private Integer id;

    @Schema(name = "nursename", description = "护理类型")
    private String nursename;//护理类型
    @Schema(name = "nursetext", description = "护理描述")
    private String nursetext;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Schema(name = "nursetime", description = "护理时间")
    private LocalDateTime nursetime;
    @Schema(name = "registerid", description = "住院编号")
    private Integer registerid;//住院编号
    @Schema(name = "name", description = "护士名字")
    private String name;//记录护士名字
    @Schema(name = "name", description = "护士记录状态")
    private String status;


}
