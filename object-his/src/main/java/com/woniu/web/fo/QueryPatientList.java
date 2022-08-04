package com.woniu.web.fo;

import com.woniu.entity.dto.PatientDto;
import com.woniu.mapper.mysql.PatientMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryPatientList {
    @Schema(name = "name",description = "患者姓名")
    private String name;

    /**
     * 查询在院患者列表
     */
    public List<PatientDto> getPatientList(String name,Integer familyid){
        PatientMysqlDao patientMapper =(PatientMysqlDao)
                ApplicationContextHolder.getApplicationContext().getBean(PatientMysqlDao.class);
        return patientMapper.getPatientList(name,familyid);
    }
}
