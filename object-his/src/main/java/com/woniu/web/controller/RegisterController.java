package com.woniu.web.controller;


import com.github.pagehelper.PageInfo;
import com.google.common.eventbus.EventBus;
import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.RegisterByFamilyDto;
import com.woniu.entity.dto.RegisterByWarnidDto;
import com.woniu.entity.dto.RegisterDto;
import com.woniu.entity.dto.UserDto;
import com.woniu.mapper.redis.RegisterRedis;
import com.woniu.service.BedService;
import com.woniu.web.anon.IdempotentToken;
import com.woniu.web.fo.*;
import com.woniu.entity.po.RegisterPo;
import com.woniu.repository.RegisterRepository;
import com.woniu.service.RegisterService;
import com.woniu.entity.converter.RegisterConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
     private RegisterService registerService;

    @Autowired
    private RegisterRepository registerRepository;

    @Resource
    private RegisterConverter registerConverter;

    @Autowired
    private RegisterRedis registerRedis;
    @Autowired
    private BedService bedService;

    @Resource
    private EventBus bus;

//    分页查询所有
    @GetMapping("/gotoRegisters")
    public PageInfo<RegisterDto> getRegisters(@RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum,
                                              @RequestParam(name = "pageSize",defaultValue = "5") Integer pageSize,
                                              @RequestParam(name = "patientid",defaultValue = "") Integer patientid){
        PageInfo<RegisterPo> registers = registerService.getRegisters(pageNum, pageSize,patientid);
        List<RegisterPo> list = registers.getList();
        List<RegisterDto> dtos = registerConverter.from(list);
        PageInfo<RegisterDto> pageInfodto = new PageInfo<>(dtos);
        pageInfodto.setTotal(registers.getTotal());
        pageInfodto.setPageNum(registers.getPageNum());
        pageInfodto.setPageSize(registers.getPageSize());
        return pageInfodto;
    }


    /**
     * 罗虎
     * @return 入院登记表列表
     */
    @GetMapping("/queryPageInfo")
    public ResponseResult<PageInfo<RegisterDto>> queryPageInfo(QueryPageInfo queryPageInfo){
        PageInfo<RegisterDto> dtoPageInfo = queryPageInfo.exec();
        return new ResponseResult<>(dtoPageInfo,"ok",2000);
    }


    /**
     * 罗虎
     * @return 角色ID为收费人员的用户列表
     */
    @GetMapping("/queryUserListByRoleId")
    public ResponseResult<List<UserDto>> queryUserListByRoleId(){
        List<UserDto> dtos = new QueryUserListByRoleId().exec();
        return new ResponseResult<>(dtos,"ok",2000);
    }


    /**
     * 罗虎
     * 添加入院信息
     * @return
     */
    @PostMapping("/publish")
    @IdempotentToken
    public ResponseResult<Void> addRegister(@RequestBody AddRegister addRegister , HttpServletRequest request ){
        return addRegister.exec();
    }


    /**
     * 罗虎
     * 修改入院信息
     * @return
     */
    @PostMapping("/modify")
    @IdempotentToken
    public ResponseResult<Void> modifyRegister(@RequestBody ModifyRegister modifyRegister , HttpServletRequest request ){
        return modifyRegister.exec();
    }

    @PostMapping("/upregister")//修改床位的方法
    public ResponseResult<Void> upRegisterbyBad(@RequestBody RegisterDto registerDto){
        try {
            registerService.upRegisterbyBad(registerDto);
            return new ResponseResult(200,"OK");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(400, "失败");
        }
    }

    @GetMapping("/upstatusc")
    public ResponseResult upRegisterBytatus(Integer id,String status){
        Boolean f = registerService.upRegisterByStatus(id,status);
        if (f){
            return new ResponseResult(200, "OK");
        }else {
            return new ResponseResult(400, "ERRO");
        }
    }

    @PostMapping("/gotoOut")//修改出院状态的
    public ResponseResult<Void> gotoregitOut(@RequestBody RegisterDto registerDto){
        //出院的同时修改床位状态
        bedService.dowBedByidStatus(registerDto.getBedid());
        try {
            registerService.upRegisterbyOut(registerDto);
            return new ResponseResult(200,"OK");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(400, "失败");
        }
    }

    /**
     * 罗虎
     * 根据ID删除入院信息
     * @return
     */
    @PostMapping("/remove/{id}")
    public ResponseResult<Boolean> removeRegister(@PathVariable("id") Integer id ){
        boolean remove = registerService.removeById(id);
        registerRedis.deleteById(id);
        return new ResponseResult<>(remove,"ok",2000);
    }

    /**
     * 罗虎
     * 根据ID查询单个入院信息
     * @return
     */
    @GetMapping("/queryById/{id}/{status}")
    public ResponseResult<RegisterDto> queryByIdRegister(@PathVariable("id") Integer id ,@PathVariable("status") Integer status){
        return new RegisterByIdQuery(id,status).exec();
    }


    /**
     * 罗虎
     * 出院结算
     * @return
     */
    @PostMapping("/settlement")
    @IdempotentToken
    public ResponseResult<Void> modifyRegisterAndMoneyList(@RequestBody OutSettlementComment outSettlementComment){
        bus.post(outSettlementComment);
        return new ResponseResult<>(2000,"ok");
    }

//   根据科室查询所有的患者，返回一个住院号就是regisid 和患者姓名
    @GetMapping("/gotolistAll")
    public List<RegisterByFamilyDto> gotoPatientList(Integer familyid){
        List<RegisterByFamilyDto> registerBFamilyid = registerRepository.getRegisterBFamilyid(familyid);
        return registerBFamilyid;
    }

    /**
     * 简单的通过住院号查询所有医嘱
     */
    @GetMapping("/getWarnids/{rid}")
    public List<RegisterByWarnidDto> getWarnids(@PathVariable("rid") Integer rid){
        List<RegisterByWarnidDto> warnids = registerRepository.getWarnids(rid);
        return warnids;
    }

}

