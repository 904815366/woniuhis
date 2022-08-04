package com.woniu.web.fo;

import com.woniu.entity.dto.ConsutantsDto;
import com.woniu.mapper.mysql.ConsutantsMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryConsutatsListByApplydeptid {
    @Schema(name = "depeId",description = "发起科室id")
    private Integer depeId;

    /**
     * 查询本科的会诊列表
     * @param deptId
     * @return
     */
    public List<ConsutantsDto> consultationByApplydeptid(Integer deptId){
        ConsutantsMysqlDao consutantsMysqlDao =
                ApplicationContextHolder.getApplicationContext().getBean(ConsutantsMysqlDao.class);
        return consutantsMysqlDao.consultationByApplydeptid(deptId);
    }

}
