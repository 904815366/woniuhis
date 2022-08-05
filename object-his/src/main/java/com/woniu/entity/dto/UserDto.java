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
@Schema(description = "用户信息")
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "用户id")
    private Integer id;
    @Schema(name = "familyid", description = "科室id")
    private Integer familyid;
    @Schema(name = "username", description = "用户名")
    private String username;
    @Schema(name = "password", description = "密码")
    private String password;
    @Schema(name = "name", description = "姓名")
    private String name;
    @Schema(name = "sex", description = "性别")
    private String sex;
    @Schema(name = "age", description = "年龄")
    private Integer age;
    @Schema(name = "card", description = "身份证号")
    private String card;
    @Schema(name = "phone", description = "电话号码")
    private String phone;
    @Schema(name = "roleid", description = "岗位id")
    private Integer roleid;
    @Schema(name = "level", description = "级别")
    private String level;

    private String status;

    private String avatar;
}
