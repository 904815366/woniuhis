package com.woniu.web.fo;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;
import com.woniu.entity.po.ConsutantsPo;
import com.woniu.service.ConsutantsService;
import com.woniu.util.ApplicationContextHolder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddApplyConsultation {
    @Schema(name = "patientname", description = "患者名称")
    private String patientname;

    @Schema(name = "registerid", description = "住院号")
    private Integer registerid;

    @Schema(name = "consutantstype", description = "会诊紧急情况（紧急，24小时，一般）")
    private String consutantstype;

    @Schema(name = "goal", description = "会诊目的")
    private String goal;

    @Schema(name = "patientcondition", description = "患者情况")
    private String patientcondition;

    @Schema(name = "consutantscondition", description = "诊疗情况")
    private String consutantscondition;

    @Schema(name = "status", description = "状态(未会诊/会诊中/已会诊)")
    private String status;

    @Schema(name = "userid", description = "会诊医生ID")
    private Integer userid;

    @Schema(name = "applydeptid", description = "申请科室")
    private Integer applydeptid;

    @Schema(name = "replydeptid", description = "回复科室")
    private Integer replydeptid;

    public void AddApplyConsultation(){
        ConsutantsPo consutantsPo = new ConsutantsPo();
        consutantsPo.setPatientname(patientname);
        consutantsPo.setRegisterid(registerid);
        consutantsPo.setConsutantstype(consutantstype);
        consutantsPo.setGoal(goal);
        consutantsPo.setPatientcondition(patientcondition);
        consutantsPo.setConsutantscondition(consutantscondition);
        consutantsPo.setStatus(status);
        consutantsPo.setUserid(userid);
        consutantsPo.setApplydeptid(applydeptid);
        consutantsPo.setReplydeptid(replydeptid);

        ConsutantsService bean = ApplicationContextHolder.getApplicationContext().getBean(ConsutantsService.class);
        bean.addApplyConsultation(consutantsPo);
    }
}
