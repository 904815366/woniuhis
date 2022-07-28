package com.woniu.entity.converter;

import com.woniu.entity.dto.FamilyDto;
import com.woniu.entity.po.FamilyPo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FamilyConverter {

    FamilyDto from(FamilyPo po);

    List<FamilyDto> from(List<FamilyPo> po);

}
