package com.woniu.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RedisHash("appointmentPo")
@TableName("appointment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentPo implements Serializable {

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

    private String diagnose;


    private Date entertime;

    private Integer familyid;

    private String regtype;

    private Integer userid;

    private Double regprice;

    private String status;



}
