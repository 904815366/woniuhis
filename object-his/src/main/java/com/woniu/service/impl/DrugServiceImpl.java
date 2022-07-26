package com.woniu.service.impl;

import com.woniu.entity.po.DrugPo;
import com.woniu.mapper.mysql.DrugMysqlDao;
import com.woniu.service.DrugService;
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
public class DrugServiceImpl extends ServiceImpl<DrugMysqlDao, DrugPo> implements DrugService {

}
