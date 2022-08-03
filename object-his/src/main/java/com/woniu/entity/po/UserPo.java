package com.woniu.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

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
@TableName("user")
@RedisHash("userpo")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer familyid;

    private String username;

    private String password;

    private String name;

    private String sex;

    private Integer age;

    private String card;

    private String phone;

    private Integer roleid;

    private String level;

    private String status;

    private String avatar;
}
