package com.woniu.web.fo;

import com.woniu.entity.dto.EmrDto;
import com.woniu.mapper.mysql.EmrMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryEmrListByRegisterId {
    private Integer registerid;

    public List<EmrDto> queryEmrList(Integer registerid){
        EmrMysqlDao bean = ApplicationContextHolder.getApplicationContext().getBean(EmrMysqlDao.class);
        List<EmrDto> dtoList = bean.getByRegisterIdList(registerid);
        return dtoList;
    }
}
