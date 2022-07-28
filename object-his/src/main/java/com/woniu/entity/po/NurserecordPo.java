package com.woniu.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("nurserecord")
public class NurserecordPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer nursetypeid;

    private String nursetext;

    private LocalDateTime nursetime;

    private Integer registerid;

    private Integer userid;

    private String status;


}
