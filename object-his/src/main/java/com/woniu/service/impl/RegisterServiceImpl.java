package com.woniu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.entity.dto.RegisterDto;
import com.woniu.entity.po.RegisterPo;
import com.woniu.repository.RegisterRepository;
import com.woniu.mapper.mysql.RegisterMysqlDao;
import com.woniu.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
@Service
@Transactional
public class RegisterServiceImpl extends ServiceImpl<RegisterMysqlDao, RegisterPo> implements RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    public PageInfo<RegisterPo> getRegisters(Integer pageNum, Integer pageSize, Integer pid){
        PageHelper.startPage(pageNum,pageSize);
        List<RegisterPo> registerPos = registerRepository.getRegisters(pid);
        return new PageInfo<>(registerPos);
    }

    @Override
    public boolean addRegister(RegisterPo po) {
        return registerRepository.addRegister(po);
    }

    @Override
    public boolean modifyRegister(RegisterPo po) {
        return registerRepository.modifyRegister(po);
    }


    @Override
    public void upRegisterbyBad(RegisterDto registerDto) {//安排床位修改方法
        LocalDateTime loc = LocalDateTime.now();
        registerDto.setEntertime(loc);//获取当前时间
        registerDto.setStatus("2");
        registerRepository.upRegisterByBed(registerDto);
    }

    @Override//修改入院申请
    public Boolean upRegisterByStatus(Integer id, String status) {
       return registerRepository.upRegistByStatu(id, status);
    }

    @Override
    public void upRegisterbyOut(RegisterDto registerDto) {//出院修改患者状态
        LocalDateTime loc = LocalDateTime.now();
        registerDto.setOuttime(loc);//获取当前时间
        registerDto.setStatus("5");
        registerRepository.upRegisterByBed(registerDto);
    }
}
