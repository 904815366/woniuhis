package com.woniu.entity.converter;

import com.woniu.entity.dto.RoleDto;
import com.woniu.entity.po.RolePo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface RoleConverter {
    @Mapping(source = "id",target = "id")
    @Mapping(source = "name",target = "name")
    List<RoleDto> listFrom(List<RolePo> rolePoList);
}
