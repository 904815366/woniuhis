package com.woniu.web.controller;


import com.github.pagehelper.PageInfo;
import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.RegisterDto;
import com.woniu.entity.dto.UserDto;
import com.woniu.web.fo.QueryPageInfo;
import com.woniu.web.fo.QueryUserListByRoleId;
import com.woniu.entity.po.RegisterPo;
import com.woniu.repository.RegisterRepository;
import com.woniu.service.RegisterService;
import com.woniu.entity.converter.RegisterConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
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



//    分页查询所有
    @GetMapping("/gotoRegisters")
    public PageInfo<RegisterDto> getRegisters(@RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum,
                                              @RequestParam(name = "pageSize",defaultValue = "5") Integer pageSize,
                                              @RequestParam(name = "patientid",defaultValue = "") Integer patientid){
        PageInfo<RegisterPo> registers = registerService.getRegisters(pageNum, pageSize,patientid);
        List<RegisterPo> list = registers.getList();
        List<RegisterDto> dtos = registerConverter.from(list);
        return new PageInfo<>(dtos);
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






}

