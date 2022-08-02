package com.woniu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.entity.po.EmrPo;

public interface EmrService extends IService<EmrPo> {
    void createEmr(EmrPo emrPo);
}
