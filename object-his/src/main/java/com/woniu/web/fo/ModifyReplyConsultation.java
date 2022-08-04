package com.woniu.web.fo;

import com.woniu.entity.po.ConsutantsPo;
import com.woniu.service.ConsutantsService;
import com.woniu.util.ApplicationContextHolder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyReplyConsultation {
    @Schema(name = "id",description = "会诊id")
    private Integer id;
    @Schema(name = "opinion",description = "会诊意见")
    private String opinion;
    @Schema(name = "replyid",description = "会诊回复人id")
    private Integer replyid;

    public void replyConsultation(){
        ConsutantsPo consutantsPo = new ConsutantsPo();
        consutantsPo.setId(id);
        consutantsPo.setOpinion(opinion);
        consutantsPo.setReplyid(replyid);

        ConsutantsService bean = ApplicationContextHolder.getApplicationContext().getBean(ConsutantsService.class);
        bean.updateReplyConsultation(consutantsPo);
    }
}
