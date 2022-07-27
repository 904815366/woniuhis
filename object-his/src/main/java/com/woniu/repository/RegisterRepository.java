package com.woniu.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniu.entity.po.AppointmentPo;
import com.woniu.entity.po.RegisterPo;
import com.woniu.mapper.mysql.AppointmentMysqlDao;
import com.woniu.mapper.mysql.RegisterMysqlDao;
import com.woniu.mapper.redis.RegisterRedis;
import io.minio.messages.ExistingObjectReplication;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor//生成必要参数的构造方法官方推荐
public class RegisterRepository {

    private final RegisterMysqlDao registerMysqlDao;
    private final AppointmentMysqlDao appointmentMysqlDao;


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


    public boolean addRegister(RegisterPo po) {
        QueryWrapper<RegisterPo> wrapper = new QueryWrapper<>();
        wrapper.eq("patientid", po.getPatientid())
                .ne("status",4);

        RegisterPo selectOne = registerMysqlDao.selectOne(wrapper);
        if (selectOne != null)
            throw new RuntimeException("当前病人信息已登记过!");

        int insert = registerMysqlDao.insert(po);
        if (insert == 0)
            throw new RuntimeException("添加登记信息失败!");

        AppointmentPo appointmentPo = AppointmentPo.builder().patientid(po.getPatientid()).status("1").build();
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("patientid",po.getPatientid());
        int updateById = appointmentMysqlDao.update(appointmentPo,updateWrapper);

        if (updateById == 0)
            throw new RuntimeException("添加预约信息状态失败!");

        return true;
    }
}
