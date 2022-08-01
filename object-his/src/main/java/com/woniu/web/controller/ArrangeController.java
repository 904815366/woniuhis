package com.woniu.web.controller;


import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.ArrangeDto;
import com.woniu.entity.dto.ArrangeInfoDto;
import com.woniu.service.ArrangeService;
import com.woniu.web.fo.ArrangeInfoList;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
@RestController
@RequestMapping("/arrange")
@Api( tags = {"排班管理"} )
public class ArrangeController {
    @Autowired
    private ArrangeService arrangeService;
    /**
     * 首页展示排班信息(本周)
     * @param arrangeInfoList
     * @return
     */
    @RequestMapping("/list")
    @Operation( summary = "查询本周排班列表", description = "查询本周排班列表", tags = {"排班管理"} )
    public ResponseResult<List<ArrangeDto>> arrangeList(ArrangeInfoList arrangeInfoList){
        List<ArrangeDto> arrangeDtoList = new ArrayList<>();
        //从数据库中查到的数据
        List<ArrangeDto> sqlArgeDtoList = arrangeInfoList.arrangeList();
        //拿到医生id的集合,放入新建nameMap的key--key=userid,value=String
        Map<String, String> nameMap = new HashMap<>();
        for (ArrangeDto sqlArgeDto : sqlArgeDtoList) {
            nameMap.put(sqlArgeDto.getDutyuserid().toString(),null);
        }
        //拿到医生姓名 岗位 科室 值班日期的集合,放入新建的infoMap--key=userid,value=ArrangeInfoDto
        Map<String, ArrangeInfoDto> infoMap = new HashMap<>();
        for (Map.Entry<String, String> entry : nameMap.entrySet()) {
            ArrangeInfoDto arrangeInfoDto = new ArrangeInfoDto();
            for (Integer i=0;i<sqlArgeDtoList.size();i++){
                ArrangeDto sqlArgeDto = sqlArgeDtoList.get(i);
                if ((entry.getKey()).equals(sqlArgeDto.getDutyuserid().toString())){
                    arrangeInfoDto.setId(sqlArgeDto.getId());
                    arrangeInfoDto.setDutyuserid(sqlArgeDto.getDutyuserid());
                    arrangeInfoDto.setName(sqlArgeDto.getName());
                    arrangeInfoDto.setFamilyid(sqlArgeDto.getFamilyid());
                    arrangeInfoDto.setFamilyname(sqlArgeDto.getFamilyname());
                    arrangeInfoDto.setRoleid(sqlArgeDto.getRoleid());
                    arrangeInfoDto.setRolename(sqlArgeDto.getRolename());
                    //封装好的info对象
                    System.out.println(arrangeInfoDto);
                    infoMap.put(entry.getKey(),arrangeInfoDto);
                    break;
                }
            }
        }
        //拿到人员id对应的值班日,存入nameMap的value,放入nameMap--key=userid,value=String取出
        // map为{key1=value1,k2=v2,...,kn=vn}
        for (Map.Entry<String, String> entry : nameMap.entrySet()) {
            String dutytimeStr = null;
            for (Integer i=0;i<sqlArgeDtoList.size();i++){
                if ((entry.getKey()).equals((sqlArgeDtoList.get(i).getDutyuserid()).toString())){
                    dutytimeStr = dutytimeStr + ","+sqlArgeDtoList.get(i).getDutytime();
                    entry.setValue(dutytimeStr);
                }
            }
        }
        for (Map.Entry<String, String> entry : nameMap.entrySet()) {
            ArrangeDto arrangeDtoInfo = new ArrangeDto();
            for (Map.Entry<String, ArrangeInfoDto> info : infoMap.entrySet()){
                if (info.getKey().equals(entry.getKey())){
                    arrangeDtoInfo.setId(info.getValue().getId());
                    arrangeDtoInfo.setDutyuserid(info.getValue().getDutyuserid());
                    arrangeDtoInfo.setName(info.getValue().getName());
                    arrangeDtoInfo.setRoleid(info.getValue().getRoleid());
                    arrangeDtoInfo.setRolename(info.getValue().getRolename());
                    arrangeDtoInfo.setFamilyid(info.getValue().getFamilyid());
                    arrangeDtoInfo.setFamilyname(info.getValue().getFamilyname());
                }
                arrangeDtoInfo.setDutyuserid(Integer.parseInt(entry.getKey()));
                //处理value(值班日期)
                String dutytimeStr = entry.getValue().replace("null,", "");
                String[] dutytime = dutytimeStr.split(",");
                for (String time : dutytime) {
                    Integer timeNum = Integer.parseInt(time);
                    if (timeNum == 1) {
                        arrangeDtoInfo.setMonday(time);
                    }
                    if (timeNum == 2) {
                        arrangeDtoInfo.setTuesday(time);
                    }
                    if (timeNum == 3) {
                        arrangeDtoInfo.setWednesday(time);
                    }
                    if (timeNum == 4) {
                        arrangeDtoInfo.setThursday(time);
                    }
                    if (timeNum == 5) {
                        arrangeDtoInfo.setFriday(time);
                    }
                    if (timeNum == 6) {
                        arrangeDtoInfo.setSaturday(time);
                    }
                    if (timeNum == 7) {
                        arrangeDtoInfo.setSunday(time);
                    }
                }
            }
            //key=value格式
            System.out.println(entry);
            System.out.println(arrangeDtoInfo);
            arrangeDtoList.add(arrangeDtoInfo);
        }
        return new ResponseResult<List<ArrangeDto>>(arrangeDtoList,"OK",200);
    }

    /**
     * 下周排班信息
     * @param arrangeInfoList
     * @return
     */
    @RequestMapping("/listnext")
    @Operation( summary = "查询下周排班列表", description = "查询下周排班列表", tags = {"排班管理"} )
    public ResponseResult<List<ArrangeDto>> arrangeListNext(ArrangeInfoList arrangeInfoList){
        List<ArrangeDto> arrangeDtoList = new ArrayList<>();
        //从数据库中查到的数据
        List<ArrangeDto> sqlArgeDtoList = arrangeInfoList.arrangeListNext();
        //拿到医生id的集合,放入新建nameMap的key--key=userid,value=String
        Map<String, String> nameMap = new HashMap<>();
        for (ArrangeDto sqlArgeDto : sqlArgeDtoList) {
            nameMap.put(sqlArgeDto.getDutyuserid().toString(),null);
        }
        //拿到医生姓名 岗位 科室 值班日期的集合,放入新建的infoMap--key=userid,value=ArrangeInfoDto
        Map<String, ArrangeInfoDto> infoMap = new HashMap<>();
        for (Map.Entry<String, String> entry : nameMap.entrySet()) {
            ArrangeInfoDto arrangeInfoDto = new ArrangeInfoDto();
            for (Integer i=0;i<sqlArgeDtoList.size();i++){
                ArrangeDto sqlArgeDto = sqlArgeDtoList.get(i);
                if ((entry.getKey()).equals(sqlArgeDto.getDutyuserid().toString())){
                    arrangeInfoDto.setId(sqlArgeDto.getId());
                    arrangeInfoDto.setDutyuserid(sqlArgeDto.getDutyuserid());
                    arrangeInfoDto.setName(sqlArgeDto.getName());
                    arrangeInfoDto.setFamilyid(sqlArgeDto.getFamilyid());
                    arrangeInfoDto.setFamilyname(sqlArgeDto.getFamilyname());
                    arrangeInfoDto.setRoleid(sqlArgeDto.getRoleid());
                    arrangeInfoDto.setRolename(sqlArgeDto.getRolename());
                    //封装好的info对象
                    System.out.println(arrangeInfoDto);
                    infoMap.put(entry.getKey(),arrangeInfoDto);
                    break;
                }
            }
        }
        //拿到人员id对应的值班日,存入nameMap的value,放入nameMap--key=userid,value=String取出
        // map为{key1=value1,k2=v2,...,kn=vn}
        for (Map.Entry<String, String> entry : nameMap.entrySet()) {
            String dutytimeStr = null;
            for (Integer i=0;i<sqlArgeDtoList.size();i++){
                if ((entry.getKey()).equals((sqlArgeDtoList.get(i).getDutyuserid()).toString())){
                    dutytimeStr = dutytimeStr + ","+sqlArgeDtoList.get(i).getDutytime();
                    entry.setValue(dutytimeStr);
                }
            }
        }
        for (Map.Entry<String, String> entry : nameMap.entrySet()) {
            ArrangeDto arrangeDtoInfo = new ArrangeDto();
            for (Map.Entry<String, ArrangeInfoDto> info : infoMap.entrySet()){
                if (info.getKey().equals(entry.getKey())){
                    arrangeDtoInfo.setId(info.getValue().getId());
                    arrangeDtoInfo.setDutyuserid(info.getValue().getDutyuserid());
                    arrangeDtoInfo.setName(info.getValue().getName());
                    arrangeDtoInfo.setRoleid(info.getValue().getRoleid());
                    arrangeDtoInfo.setRolename(info.getValue().getRolename());
                    arrangeDtoInfo.setFamilyid(info.getValue().getFamilyid());
                    arrangeDtoInfo.setFamilyname(info.getValue().getFamilyname());
                }
                arrangeDtoInfo.setDutyuserid(Integer.parseInt(entry.getKey()));
                //处理value(值班日期)
                String dutytimeStr = entry.getValue().replace("null,", "");
                String[] dutytime = dutytimeStr.split(",");
                for (String time : dutytime) {
                    Integer timeNum = Integer.parseInt(time);
                    if (timeNum == 1) {
                        arrangeDtoInfo.setMonday(time);
                    }
                    if (timeNum == 2) {
                        arrangeDtoInfo.setTuesday(time);
                    }
                    if (timeNum == 3) {
                        arrangeDtoInfo.setWednesday(time);
                    }
                    if (timeNum == 4) {
                        arrangeDtoInfo.setThursday(time);
                    }
                    if (timeNum == 5) {
                        arrangeDtoInfo.setFriday(time);
                    }
                    if (timeNum == 6) {
                        arrangeDtoInfo.setSaturday(time);
                    }
                    if (timeNum == 7) {
                        arrangeDtoInfo.setSunday(time);
                    }
                }
            }
            //key=value格式
            System.out.println(entry);
            System.out.println(arrangeDtoInfo);
            arrangeDtoList.add(arrangeDtoInfo);
        }
        return new ResponseResult<List<ArrangeDto>>(arrangeDtoList,"OK",200);
    }


    @RequestMapping("/addArr")
    @Operation( summary = "下周新增排班", description = "下周新增排班", tags = {"排班管理"} )
    public ResponseResult<Void> addArr(@RequestParam("dutyuserid")Integer dutyuserid,
                                           @RequestParam("checkListStr")String checkListStr,
                                           @RequestParam("createuserid")Integer createuserid,
                                           @RequestParam("week")String week){
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            arrangeService.addArr(dutyuserid,checkListStr,createuserid,week);
            responseResult = new ResponseResult<>(200,"OK");
        }catch (RuntimeException e){
            e.printStackTrace();
            responseResult = new ResponseResult<>(006,e.getMessage());
        }
        return responseResult;
    }
}

