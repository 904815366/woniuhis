package com.woniu.service.impl;

import com.woniu.entity.po.WarnPo;
import com.woniu.mapper.mysql.WarnMysqlDao;
import com.woniu.service.WarnService;
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
public class WarnServiceImpl extends ServiceImpl<WarnMysqlDao, WarnPo> implements WarnService {

}
