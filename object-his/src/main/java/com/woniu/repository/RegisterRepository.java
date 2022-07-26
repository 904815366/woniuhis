package com.woniu.repository;

import com.woniu.entity.po.RegisterPo;
import com.woniu.mapper.mysql.RegisterMysqlDao;
import com.woniu.mapper.redis.RegisterRedis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor//生成必要参数的构造方法官方推荐
public class RegisterRepository {

    private final RegisterMysqlDao registerMysqlDao;

    private final RegisterRedis registerRedisDa;

//    简单的增加办法

//    简单的删除方法

//    简单的修改方法

//    查询所有方法
    public List<RegisterPo> getRegisters(Integer pid){

        List<RegisterPo> registerPos = registerMysqlDao.queryRegisters(pid);

        return registerPos;
    }
    
//    通过编号查询单个患者的缴费状态
    public RegisterPo getRegister(Integer patientid){
        RegisterPo registerPo = registerMysqlDao.queryRegisterByPatientid(patientid);
        return registerPo;
    }



}
