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
@Schema(description = "用户科室关联dto")
public class UserByFamilyDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "id")
    private Integer id;
    @Schema(name = "familyid", description = "科室id")
    private Integer familyid;
    @Schema(name = "name", description = "科室名字")
    private String name;
    @Schema(name = "roleid", description = "角色id")
    private Integer roleid;

}
