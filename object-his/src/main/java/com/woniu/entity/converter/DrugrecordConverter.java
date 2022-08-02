package com.woniu.entity.converter;

import com.github.pagehelper.PageInfo;
import com.woniu.entity.dto.DrugrecordDto;
import com.woniu.entity.po.DrugrecordPo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface DrugrecordConverter {
    DrugrecordDto con(DrugrecordPo po);
    List<DrugrecordDto> listFrom (List<DrugrecordPo> drugrecordPoList);
    PageInfo<DrugrecordDto> pageInfoFrom(PageInfo<DrugrecordPo> pageInfo);
}
