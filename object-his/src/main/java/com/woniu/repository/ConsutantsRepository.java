package com.woniu.repository;

import com.woniu.entity.dto.ConsutantsDto;
import com.woniu.entity.po.ConsutantsPo;
import com.woniu.mapper.mysql.ConsutantsMysqlDao;
import com.woniu.mapper.mysql.WarnMysqlDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsutantsRepository {
    //使用依赖注入生成全参构造方法
    private final ConsutantsMysqlDao consutantsMysqlDao;

    /**
     * 发起会诊
     * @param consutantsPo
     */
    public void addApplyConsultation(ConsutantsPo consutantsPo){
        consutantsMysqlDao.addApplyConsultation(consutantsPo);
    }

    /**
     * 回复会诊
     * @param consutantsPo
     */
    public void updateReplyConsultation(ConsutantsPo consutantsPo){
        consutantsMysqlDao.updateReplyConsultation(consutantsPo);
    }

    /**
     * 修改会诊
     * @param consutantsPo
     */
    public void updateApplyConsultation(ConsutantsPo consutantsPo){
        consutantsMysqlDao.updateApplyConsultation(consutantsPo);
    }

    /**
     * 删除会诊
     * @param id
     */
    public void removeById(Integer id){
        consutantsMysqlDao.removeById(id);
    }
}
