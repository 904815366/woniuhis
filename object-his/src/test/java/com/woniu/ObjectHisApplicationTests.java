package com.woniu;

import com.woniu.entity.dto.EmrDto;
import com.woniu.entity.dto.WarnDto;
import com.woniu.mapper.mysql.EmrMysqlDao;
import com.woniu.mapper.mysql.WarnMysqlDao;
import com.woniu.web.fo.QueryEmrListByRegisterId;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@SpringBootTest
class ObjectHisApplicationTests {

    @Autowired
    private EmrMysqlDao  emrMysqlDao;
    @Test
    void contextLoads() {
        List<EmrDto> byRegisterIdList = emrMysqlDao.getByRegisterIdList(22);
        for (EmrDto emrDto : byRegisterIdList) {
            System.out.println(emrDto);
        }

    }


}
