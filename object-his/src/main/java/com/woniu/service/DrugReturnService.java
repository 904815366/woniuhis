package com.woniu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.entity.po.DrugReturnPo;

public interface DrugReturnService extends IService<DrugReturnPo> {
    void returnAdd(Integer id, Integer returnnum);

    void deleteById(String[] idArr);
}
