package com.woniu.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "没有排班安排的员工名单")
public class NullArrUserDto {
    @Schema(name = "id", description = "员工ID")
    private Integer id;
    @Schema(name = "name", description = "员工名")
    private String name;
    @Schema(name = "familyid", description = "科室ID")
    private Integer familyid;
    @Schema(name = "roleid", description = "岗位ID")
    private Integer roleid;
    @Schema(name = "arrangeInfo", description = "排班信息字符串")
    private String arrangeInfo;
}
