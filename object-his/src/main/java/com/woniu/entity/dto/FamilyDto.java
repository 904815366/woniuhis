package com.woniu.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@Schema(description = "科室信息")
public class FamilyDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "科室")
    private Integer id;
    @Schema(name = "name", description = "科室名")
    private String familyname;


}
