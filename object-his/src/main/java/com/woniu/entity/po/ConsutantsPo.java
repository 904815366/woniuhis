package com.woniu.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@RedisHash("consutants")
public class ConsutantsPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Id
    private Integer id;

    private String patientname;

    private Integer registerid;

    private String consutantstype;

    private String goal;

    private String patientcondition;

    private String consutantscondition;

    private String status;

    //发起医生id
    private Integer userid;

    //回复内容
    private String opinion;

    //回复医生
    private Integer replyid;

    //发起科室id
    private Integer applydeptid;

    //回复科室id
    private Integer replydeptid;



}
