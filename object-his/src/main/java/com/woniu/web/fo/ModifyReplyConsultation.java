package com.woniu.web.fo;

import com.woniu.entity.po.ConsutantsPo;
import com.woniu.service.ConsutantsService;
import com.woniu.util.ApplicationContextHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyReplyConsultation {
    private Integer id;
    private String opinion;
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
