package com.woniu;

import com.woniu.entity.dto.EmrDto;
import com.woniu.entity.dto.WarnDto;
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

    @Test
    void contextLoads() {
        String s = "";
        String[] split = s.split(",");

        System.out.println(split.length);

    }


    @Test
    void judge() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Soldier(arr, (Integer n) -> n % 2 == 0);
    }

    @Test
    void Soldier(Integer[] arr, Predicate<Integer> predicate) {
        for (Integer integer : arr) {
            if (predicate.test(integer)) {
                System.out.println(integer);
            }
        }

    }


}
