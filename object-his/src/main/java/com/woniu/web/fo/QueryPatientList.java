package com.woniu.web.fo;

import com.woniu.entity.dto.PatientDto;
import com.woniu.mapper.mysql.PatientMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryPatientList {
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
