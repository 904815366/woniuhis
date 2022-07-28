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
    public void upRegisterbyBad(RegisterDto registerDto) {//安排床位修改方法
        registerRepository.upRegisterByBed(registerDto);
    }
}
