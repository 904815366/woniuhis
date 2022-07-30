package com.woniu.service;

import com.woniu.entity.po.ConsutantsPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
public interface ConsutantsService extends IService<ConsutantsPo> {
    void addApplyConsultation(ConsutantsPo consutantsPo);

    void updateReplyConsultation(ConsutantsPo consutantsPo);

    void updateApplyConsultation(ConsutantsPo consutantsPo);

    void removeById(Integer id);
}
