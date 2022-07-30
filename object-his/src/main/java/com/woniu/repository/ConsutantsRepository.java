package com.woniu.repository;

import com.woniu.entity.dto.ConsutantsDto;
import com.woniu.mapper.mysql.ConsutantsMysqlDao;
import com.woniu.mapper.mysql.WarnMysqlDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsutantsRepository {
    //使用依赖注入生成全参构造方法
    private final ConsutantsMysqlDao consutantsMysqlDao;

}
