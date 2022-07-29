package com.woniu.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Schema(description = "首页排班信息详情(用于返回值转换)")
public class ArrangeInfoDto {
    @Schema(name = "id", description = "值班表ID")
    private Integer id;
    @Schema(name = "dutyuserid", description = "值班人员ID")
    private Integer dutyuserid;
    @Schema(name = "name", description = "员工姓名")
    private String name;
    @Schema(name = "familyid", description = "科室id")
    private Integer familyid;
    @Schema(name = "familyname", description = "科室名")
    private String familyname;
    @Schema(name = "roleid", description = "岗位id")
    private Integer roleid;
    @Schema(name = "rolename", description = "岗位名")
    private String rolename;
}
