package com.woniu.web.fo;

import com.woniu.entity.dto.PatientDto;
import com.woniu.mapper.mysql.PatientMapper;
import com.woniu.service.PatientService;
import com.woniu.util.ApplicationContextHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryPatientById {
    private Integer id;

    /**
     * 根据id查询患者
     * @param id
     * @return
     */
    public PatientDto getPatientById(Integer id){
        PatientService patientService =(PatientService)
                ApplicationContextHolder.getApplicationContext().getBean(PatientService.class);
         return patientService.getByPatientId(id);
    }
}
