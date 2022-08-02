package com.woniu.service.impl;

import com.woniu.entity.po.ArrangePo;
import com.woniu.mapper.mysql.ArrangeMysqlDao;
import com.woniu.repository.ArrangeRepository;
import com.woniu.repository.UserRepository;
import com.woniu.service.ArrangeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.util.ApplicationContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ArrangeServiceImpl extends ServiceImpl<ArrangeMysqlDao, ArrangePo> implements ArrangeService {

    @Override
    public void addArr(Integer dutyuserid, String checkListStr,Integer createuserid,String week) {
        ArrangeRepository arrangeRepository = ApplicationContextHolder.
                getApplicationContext().getBean(ArrangeRepository.class);
        //处理数字字符串
        String[] checkListArr = new String[0];
        if (!checkListStr.isEmpty()){
            checkListArr = checkListStr.split(",");
        }
        Boolean addResult = arrangeRepository.addArr(dutyuserid, checkListArr, createuserid,week);
        if (addResult==false){
            throw new RuntimeException("新增排班失败");
        }
    }

    @Override
    public void editArr(Integer dutyuserid, String checkListStr, Integer createuserid, String week) {
        ArrangeRepository arrangeRepository = ApplicationContextHolder.
                getApplicationContext().getBean(ArrangeRepository.class);
        System.out.println(checkListStr);
        //处理数字字符串
        String[] checkListArr = checkListStr.split(",");
        Boolean editResult = arrangeRepository.editArr(dutyuserid, checkListArr, createuserid,week);
        if (editResult==false){
            throw new RuntimeException("编辑排班失败");
        }
    }
}
