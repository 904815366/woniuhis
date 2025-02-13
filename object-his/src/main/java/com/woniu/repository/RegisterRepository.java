package com.woniu.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniu.entity.dto.RegisterByFamilyDto;
import com.woniu.entity.dto.RegisterByWarnidDto;
import com.woniu.entity.po.AppointmentPo;
import com.woniu.entity.dto.RegisterDto;
import com.woniu.entity.po.RegisterPo;
import com.woniu.mapper.mysql.AppointmentMysqlDao;
import com.woniu.mapper.mysql.RegisterMysqlDao;
import com.woniu.mapper.redis.RegisterRedis;
import com.woniu.web.fo.InsertMoneyRecordComment;
import com.woniu.web.fo.ModifyRegisterMoneyComment;
import com.woniu.web.fo.ModifyStatusAndRegisterMoneyComment;
import com.woniu.web.fo.OutSettlementComment;
import io.minio.messages.ExistingObjectReplication;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Repository
@RequiredArgsConstructor//生成必要参数的构造方法官方推荐
public class RegisterRepository {

    private final RegisterMysqlDao registerMysqlDao;
    private final AppointmentMysqlDao appointmentMysqlDao;
    private final RegisterRedis registerRedis;


//    简单的通过住院号查询患者的所有医嘱id
    public List<RegisterByWarnidDto> getWarnids(Integer rid){
        List<RegisterByWarnidDto> dtos = registerMysqlDao.queryByWarnid(rid);
        return dtos;
    }

//    简单的通过科室查患者方法
    public List<RegisterByFamilyDto> getRegisterBFamilyid(Integer familyid){
        List<RegisterByFamilyDto> registerByFamilyDtos = registerMysqlDao.queryRegiPByFarmiy(familyid);
        return registerByFamilyDtos;
    }

//    简单的修改方法
    public void upRegisterByBed(RegisterDto registerDto){
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
                .ne("status",5);

        RegisterPo selectOne = registerMysqlDao.selectOne(wrapper);
        if (selectOne != null)
            throw new RuntimeException("当前病人信息已登记过!");

        int insert = registerMysqlDao.insert(po);
        if (insert == 0)
            throw new RuntimeException("添加入院登记信息失败!");

        AppointmentPo appointmentPo = AppointmentPo.builder().status("1").build();
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("patientid",po.getPatientid());
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

    public RegisterPo queryByIdRegister(Integer id,Integer status) {
        QueryWrapper<RegisterPo> wrapper = new QueryWrapper();
        wrapper.eq("id",id);
        if (status == 3){
            wrapper.eq("status",status);
        }
        if (status == 5){
            wrapper.ne("status",status);
        }

        RegisterPo po = registerMysqlDao.selectOne(wrapper);
            if (po == null){
                throw new NullPointerException("queryByIdRegister根据ID为查询到数据...");
            }
            return po;

    }

//    修改患者的出院申请
    public Boolean upRegistByStatu(Integer id,String status){
       return registerMysqlDao.upRegisterByStatus(id,status);
    }

    public void modifyMoney(ModifyRegisterMoneyComment modifyRegisterMoneyComment) {
        boolean modifyMoney = registerMysqlDao.modifyMoney(modifyRegisterMoneyComment);
        if (modifyMoney == false){
            throw new RuntimeException("增加余额失败..");
        }
    }

    /**
     * 罗虎
     * 出院结算 , 减余额,修改状态为出院审核通过
     * @return
     */
    public boolean modifyMoneyAndStatusById(OutSettlementComment outSettlementComment) {
        boolean modifyResult = registerMysqlDao.UpdateMoneyAndStatusById(outSettlementComment);
        return modifyResult;
    }

    /**
     * 查询状态为住院中2/申请出院3/审核通过4的ID列表
     * @return
     */
    public List<RegisterPo> queryStatus() {
        QueryWrapper<RegisterPo> wrapper = new QueryWrapper();
        wrapper.eq("status",2)
                .or().eq("status",3)
                .or().eq("status",4);
        return registerMysqlDao.selectList(wrapper);
    }

    public void modifyRegisterMoneyAndUsed(ModifyStatusAndRegisterMoneyComment modifyStatusAndRegisterMoneyComment) {
        Integer update = registerMysqlDao.updateRegisterMoneyAndUsed(modifyStatusAndRegisterMoneyComment);
            if (update == 0){
                throw new RuntimeException("updateRegisterMoneyAndUsed执行结果为0");
            }
//        RegisterPo registerPo = registerMysqlDao.selectById(modifyStatusAndRegisterMoneyComment.getRegisterid());

//            if (registerPo.getMoney() <= 0){
//                registerPo.setStatus("0");
//            }else{
//                registerPo.setStatus("1");
//            }
//        registerMysqlDao.updateById(registerPo);
    }
}
