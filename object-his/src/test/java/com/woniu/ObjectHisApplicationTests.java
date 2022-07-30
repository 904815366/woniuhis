package com.woniu;

import com.woniu.entity.dto.WarnDto;
import com.woniu.mapper.mysql.WarnMysqlDao;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ObjectHisApplicationTests {

    @Test
    void contextLoads() {
        String s = "";
        String[] split = s.split(",");

            System.out.println(split.length);

    }

}
