package com.woniu.web.controller;


import com.woniu.entity.dto.BedDto;
import com.woniu.entity.dto.FamilyDto;
import com.woniu.entity.dto.WardDto;
import com.woniu.entity.po.BedPo;
import com.woniu.entity.po.FamilyPo;
import com.woniu.entity.po.WardPo;
import com.woniu.repository.BedRepository;
import com.woniu.entity.converter.BedConverter;
import com.woniu.entity.converter.FamilyConverter;
import com.woniu.entity.converter.WardConverter;
import com.woniu.service.BedService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/bed")
@Api( tags = {"床位管理"} )
public class BedController {

    @Autowired
    private BedRepository bedRepository;

    @Resource
    private BedConverter bedConverter;

    @Resource
    private FamilyConverter familyConverter;

    @Resource
    private WardConverter wardConverter;

    @Autowired
    private BedService bedService;

//    查询所有的科室
    @GetMapping("/famiy")
    @Operation( summary = "先查询所有科室供护士选择", description = "先查询所有科室供护士选择", tags = {"床位管理"} )
    public List<FamilyDto> getFamiy(){
        List<FamilyPo> familyPoList = bedRepository.qureyFamiy();
        List<FamilyDto> familyDtos = familyConverter.from(familyPoList);
        return familyDtos;
    }

    @GetMapping("/ward")
    @Operation( summary = "根据科室id查询所有病房", description = "根据科室id查询所有病房", tags = {"床位管理"} )
    public List<WardDto> getWardByfid(Integer fid){
        List<WardPo> wardPoList = bedRepository.qureyWardByfid(fid);
        List<WardDto> dtos = wardConverter.from(wardPoList);
        return dtos;
    }

    @GetMapping("/bed")
    @Operation( summary = "根据病房id查询所有床位", description = "根据病房id查询所有床位", tags = {"床位管理"} )
    public List<BedDto> getBedBywid(Integer wid){
        List<BedPo> bedPoList = bedRepository.qureyBedBywid(wid);
        List<BedDto> dtos = bedConverter.from(bedPoList);
        return dtos;
    }

    @GetMapping("/upbedstats")
    @Operation( summary = "根据护士选择的床位id将床位状态成被占用状态", description = "根据护士选择的床位id将床位状态成被占用状态", tags = {"床位管理"} )
    public void upBedStatus(Integer bid){//修改床位状态
        bedService.upBedByidStatus(bid);
    }


}

