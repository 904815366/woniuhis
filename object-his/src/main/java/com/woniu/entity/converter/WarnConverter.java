package com.woniu.entity.converter;

import com.github.pagehelper.PageInfo;
import com.woniu.entity.dto.DrugDto;
import com.woniu.entity.dto.WarnDto;
import com.woniu.entity.po.DrugPo;
import com.woniu.entity.po.WarnPo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface WarnConverter {
    WarnDto con(WarnPo po);
    List<WarnDto> listFrom (List<WarnPo> warnPoList);
    PageInfo<WarnDto> pageInfoFrom(PageInfo<WarnPo> pageInfo);
}
