package com.woniu.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
@Schema(description = "首页排班信息")
@RedisHash("arrange")
public class ArrangeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Id
    @Schema(name = "id", description = "排班ID")
    private Integer id;
    @Schema(name = "dutyuserid", description = "值班人员ID")
    private Integer dutyuserid;
    @Schema(name = "dutytime", description = "值班星期")
    private Integer dutytime;
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
    @Schema(name = "monday", description = "周一")
    private String monday;
    @Schema(name = "tuesday", description = "周二")
    private String tuesday;
    @Schema(name = "wednesday", description = "周三")
    private String wednesday;
    @Schema(name = "thursday", description = "周四")
    private String thursday;
    @Schema(name = "friday", description = "周五")
    private String friday;
    @Schema(name = "saturday", description = "周六")
    private String saturday;
    @Schema(name = "sunday", description = "周日")
    private String sunday;
}
