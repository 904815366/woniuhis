package com.woniu.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "查询在院患者时返回的信息")
@RedisHash("PatientDto")
public class PatientDto {

    @Schema(name = "id", description = "患者id")
    @Id
    private Integer id;
    @Schema(name = "bedid", description = "床号")
    private Integer bedid;
    @Schema(name = "name", description = "姓名")
    private String name;
    @Schema(name = "sex", description = "性别")
    private String sex;
    @Schema(name = "age", description = "年龄")
    private Integer age;
    @Schema(name="diagnose",description="诊断")
    private String diagnose;
    @Schema(name = "area", description = "现住址")
    private String area;
    @Schema(name = "familyname", description = "科室")
    private String familyname;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Schema(name = "entertime", description = "入院时间")
    private Date entertime;
}
