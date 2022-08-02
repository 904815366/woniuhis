package com.woniu.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author lh
 * @since 2022-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("register")
@RedisHash("RegisterPo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer patientid;

    private String name;

    private String sex;

    private Integer age;

    private String phone;

    private String card;

    private String area;

    private String diagnose;

    private Integer familyid;

    private Integer bedid;

    private Double money;

    private Integer doctorid;

    private Integer chargeid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime entertime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime outtime;

    private String status;
    private Integer wardid;


    private Double used;
    private Double refund;

}
