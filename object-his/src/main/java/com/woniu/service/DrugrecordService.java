package com.woniu.service;

import com.woniu.entity.po.DrugrecordPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
public interface DrugrecordService extends IService<DrugrecordPo> {


    void addDrugrecord(String[] idArr, Integer userid, Integer status,String type,Integer nurseid);

    void addDrugReturnrecord(String[] idArr, Integer userid);

    void addDrugRefuserecord(String[] idArr);
}
