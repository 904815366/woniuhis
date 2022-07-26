package com.woniu.service.impl;

import com.woniu.entity.po.FamilyPo;
import com.woniu.mapper.mysql.FamilyMysqlDao;
import com.woniu.service.FamilyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
@Service
public class FamilyServiceImpl extends ServiceImpl<FamilyMysqlDao, FamilyPo> implements FamilyService {

}
