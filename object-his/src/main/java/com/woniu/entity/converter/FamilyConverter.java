package com.woniu.entity.converter;

import com.woniu.entity.dto.FamilyDto;
import com.woniu.entity.dto.RoleDto;
import com.woniu.entity.po.FamilyPo;
import com.woniu.entity.po.RolePo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface FamilyConverter {
    @Mapping(source = "id",target = "id")
    @Mapping(source = "familyname",target = "familyname")
    List<FamilyDto> listFrom(List<FamilyPo> familyPoList);
}
