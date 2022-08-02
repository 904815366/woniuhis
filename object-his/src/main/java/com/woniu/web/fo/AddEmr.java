package com.woniu.web.fo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.woniu.entity.po.EmrPo;
import com.woniu.service.EmrService;
import com.woniu.util.ApplicationContextHolder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddEmr {
    private static final long serialVersionUID = 1L;
    @Schema(name = "name", description = "记录名称")
    private String name;
    @Schema(name = "id",description ="患者id" )
    private Integer registerid;
    @Schema(name = "content",description = "记录内容")
    private String content;
    @Schema(name = "userid",description = "创建人")
    private Integer userid;

    public void addEmr(){
        EmrPo emrPo = new EmrPo();
        emrPo.setName(name);
        emrPo.setRegisterid(registerid);
        emrPo.setContent(content);
        emrPo.setUserid(userid);
        EmrService bean = ApplicationContextHolder.getApplicationContext().getBean(EmrService.class);
        bean.createEmr(emrPo);
    }
}
