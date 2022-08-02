package com.woniu;

import com.woniu.entity.dto.EmrDto;
import com.woniu.entity.dto.WarnDto;
import com.woniu.mapper.mysql.WarnMysqlDao;
import com.woniu.web.fo.QueryEmrListByRegisterId;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ObjectHisApplicationTests {

    @Test
    void contextLoads() {
        List<EmrDto> emrDtos = new QueryEmrListByRegisterId().queryEmrList(1);
        for (EmrDto emrDto : emrDtos) {
            System.out.println(emrDto);
        }
    }

}
