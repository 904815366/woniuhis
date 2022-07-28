package com.woniu.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniu.entity.po.AppointmentPo;
import com.woniu.entity.dto.RegisterDto;
import com.woniu.entity.po.RegisterPo;
import com.woniu.mapper.mysql.AppointmentMysqlDao;
import com.woniu.mapper.mysql.RegisterMysqlDao;
import com.woniu.mapper.redis.RegisterRedis;
import io.minio.messages.ExistingObjectReplication;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Repository
@RequiredArgsConstructor//生成必要参数的构造方法官方推荐
public class RegisterRepository {

    private final RegisterMysqlDao registerMysqlDao;
    private final AppointmentMysqlDao appointmentMysqlDao;
    private final RegisterRedis registerRedis;


//    简单的增加办法

//    简单的删除方法

//    简单的修改方法
    public void upRegisterByBed(RegisterDto registerDto){
        LocalDateTime loc = LocalDateTime.now();
        registerDto.setEntertime(loc);//获取当前时间
        registerMysqlDao.upRegister(registerDto);
    }

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


    /**
     * 添加入院登记信息 , 添加成功后修改预约信息的状态
     * @param po
     * @return
     */
    public boolean addRegister(RegisterPo po) {
        QueryWrapper<RegisterPo> wrapper = new QueryWrapper<>();
        wrapper.eq("patientid", po.getPatientid())
                .ne("status",4);

        RegisterPo selectOne = registerMysqlDao.selectOne(wrapper);
        if (selectOne != null)
            throw new RuntimeException("当前病人信息已登记过!");

        int insert = registerMysqlDao.insert(po);
        if (insert == 0)
            throw new RuntimeException("添加入院登记信息失败!");

        AppointmentPo appointmentPo = AppointmentPo.builder().status("1").build();
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",po.getPatientid());
        int updateById = appointmentMysqlDao.update(appointmentPo,updateWrapper);

        if (updateById == 0)
            throw new RuntimeException("添加预约信息状态失败!");

        return true;
    }


    /**
     * 罗虎
     * 通过ID修改入院登记信息 , 修改成功后通过ID删除redis中的数据
     * @return
     */
    public boolean modifyRegister(RegisterPo po) {
        int update = registerMysqlDao.updateById(po);
        if (update == 0)
            throw new RuntimeException("修改入院登记信息失败!");

        registerRedis.deleteById(po.getId());
        return true;
    }
}
