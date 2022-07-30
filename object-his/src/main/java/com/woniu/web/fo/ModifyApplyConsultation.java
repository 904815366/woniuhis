package com.woniu.web.fo;

import com.woniu.entity.po.ConsutantsPo;
import com.woniu.service.ConsutantsService;
import com.woniu.util.ApplicationContextHolder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyApplyConsultation {
    @Schema(name = "id", description = "会诊ID")
    private Integer id;

    @Schema(name = "consutantstype", description = "会诊紧急情况（紧急，24小时，一般）")
    private String consutantstype;

    @Schema(name = "goal", description = "会诊目的")
    private String goal;

    @Schema(name = "patientcondition", description = "患者情况")
    private String patientcondition;

    @Schema(name = "consutantscondition", description = "诊疗情况")
    private String consutantscondition;


    @Schema(name = "replydeptid", description = "申请科室")
    private Integer replydeptid;

    public void modifyApplyConsultation(){
        ConsutantsPo consutantsPo = new ConsutantsPo();
        consutantsPo.setConsutantstype(consutantstype);
        consutantsPo.setGoal(goal);
        consutantsPo.setPatientcondition(patientcondition);
        consutantsPo.setConsutantscondition(consutantscondition);
        consutantsPo.setReplydeptid(replydeptid);
        consutantsPo.setId(id);
        ConsutantsService bean = ApplicationContextHolder.getApplicationContext().getBean(ConsutantsService.class);
        bean.updateApplyConsultation(consutantsPo);
    }
}
