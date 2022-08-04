package com.woniu.web.fo;

import com.woniu.entity.dto.PatientDto;
import com.woniu.service.PatientService;
import com.woniu.util.ApplicationContextHolder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryPatientById {
    @Schema(name = "id",description = "患者id")
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
