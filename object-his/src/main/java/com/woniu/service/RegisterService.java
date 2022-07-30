package com.woniu.service;

import com.woniu.entity.dto.RegisterDto;
import com.woniu.entity.po.RegisterPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.woniu.entity.po.RolePerms;
import com.woniu.web.fo.InsertMoneyRecordComment;
import com.woniu.web.fo.ModifyRegisterMoneyComment;
import com.woniu.web.fo.OutSettlementComment;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
public interface RegisterService  extends IService<RegisterPo>  {
    PageInfo<RegisterPo> getRegisters(Integer pageNum, Integer pageSize, Integer pid);

    boolean addRegister(RegisterPo po);

    void upRegisterbyBad(RegisterDto registerDto);

    boolean modifyRegister(RegisterPo po);

    Boolean upRegisterByStatus(Integer id,String status);

    void upRegisterbyOut(RegisterDto registerDto);


    void modifyMoney(ModifyRegisterMoneyComment modifyRegisterMoneyComment);

    void  modifyMoneyAndStatusById(OutSettlementComment outSettlementComment);
}
