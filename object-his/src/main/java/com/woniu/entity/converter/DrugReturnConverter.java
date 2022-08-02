package com.woniu.entity.converter;

import com.github.pagehelper.PageInfo;
import com.woniu.entity.dto.DrugReturnDto;
import com.woniu.entity.po.DrugReturnPo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface DrugReturnConverter {
    DrugReturnDto con(DrugReturnPo po);
    List<DrugReturnDto> listFrom (List<DrugReturnPo> drugReturnPoList);
    PageInfo<DrugReturnDto> pageInfoFrom(PageInfo<DrugReturnPo> pageInfo);
}
