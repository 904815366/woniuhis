package com.woniu.entity.converter;

import com.github.pagehelper.PageInfo;
import com.woniu.entity.dto.DrugDto;
import com.woniu.entity.po.DrugPo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface DrugConverter {
    DrugDto con(DrugPo po);
    List<DrugDto> listFrom (List<DrugPo> drugPoList);
    PageInfo<DrugDto> pageInfoFrom(PageInfo<DrugPo> pageInfo);
}
