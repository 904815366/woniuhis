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
@NoArgsConstructor
@AllArgsConstructor
public class QueryConsutatsListByReplydeptid {
    @Schema(name = "depeId",description = "回复科室id")
    private Integer depeId;

    /**
     * 查询本科回复列表
     * @param deptId
     * @return
     */
    public List<ConsutantsDto> consultationByReplydeptid(Integer deptId){
        ConsutantsMysqlDao consutantsMysqlDao =
                ApplicationContextHolder.getApplicationContext().getBean(ConsutantsMysqlDao.class);
        return consutantsMysqlDao.consultationByReplydeptid(deptId);
    }
}
