package com.woniu.web.fo;

import com.woniu.entity.dto.WarnDto;
import com.woniu.mapper.mysql.WardMysqlDao;
import com.woniu.mapper.mysql.WarnMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryWarnList {
    @Schema(name = "patientid",description = "患者id")
    private Integer patientid;

    /**
     * 查询患者的医嘱
     * @param patientid
     * @return
     */
    public List<WarnDto> getWsrnList(Integer patientid){
        WarnMysqlDao warnMysqlDao = ApplicationContextHolder.getApplicationContext().getBean(WarnMysqlDao.class);
        return warnMysqlDao.selectAllByPatientId(patientid);
    }
}
