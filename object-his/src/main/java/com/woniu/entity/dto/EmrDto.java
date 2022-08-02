package com.woniu.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "入院记录")
public class EmrDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(name = "id", description = "入院记录id")
    private Integer id;
    @Schema(name = "name", description = "记录名称")
    private String name;
    @Schema(name = "id",description ="患者id" )
    private Integer registerid;
    @Schema(name = "content",description = "记录内容")
    private String content;
    @Schema(name = "createdate",description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createdate;
    @Schema(name = "username",description = "创建人")
    private String username;
}
