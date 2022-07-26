package com.woniu.service.impl;

import com.woniu.entity.po.DrugrecordPo;
import com.woniu.mapper.mysql.DrugrecordMysqlDao;
import com.woniu.service.DrugrecordService;
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
public class DrugrecordServiceImpl extends ServiceImpl<DrugrecordMysqlDao, DrugrecordPo> implements DrugrecordService {

}
