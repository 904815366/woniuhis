package com.woniu.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.woniu.entity.dto.PermsDto;
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
@RedisHash("permsPo")
public class PermsPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String modulename;

    private Integer parentid;

    private String type;

    private String link;

    private String percode;

    private String fonticon;

    @TableField(exist = false)
    private List<PermsPo> children;
}
