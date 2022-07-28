package com.woniu.repository;

import com.woniu.entity.dto.NurserecordDto;
import com.woniu.mapper.mysql.NurserecordMysqlDao;
import com.woniu.mapper.redis.NurseecordRedis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NurserecordRepository {

    private final NurserecordMysqlDao nurserecordMysqlDao;

    private final NurseecordRedis nurseecordRedis;

//    简单的增加护理记录的方法
    public void addNurserecord(){

    }

//    查询所有的护理记录表
    public List<NurserecordDto> getNursercordDtos(){
        List<NurserecordDto> nurserecordDtos = nurserecordMysqlDao.querNurserecords();
        return nurserecordDtos;
    }

}
