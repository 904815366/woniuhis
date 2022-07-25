package com.woniu.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class Register implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer patientid;

    private String area;

    private String diagnose;

    private Integer familyid;

    private Integer bedid;

    private Double money;

    private Integer doctorid;

    private Integer chargeid;

    private LocalDateTime entertime;

    private LocalDateTime outtime;

    private String status;


}
