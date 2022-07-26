package com.woniu.entity.converter;

import com.woniu.entity.dto.UserDto;
import com.woniu.entity.po.UserPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface UserConverter {
    @Mapping(source = "id",target = "id")
    @Mapping(source = "username",target = "username")
    @Mapping(source = "password",target = "password")
    @Mapping(source = "name",target = "name")
    @Mapping(source = "sex",target = "sex")
    @Mapping(source = "age",target = "age")
    @Mapping(source = "card",target = "card")
    @Mapping(source = "phone",target = "phone")
    @Mapping(source = "roleid",target = "roleid")
    @Mapping(source = "level",target = "level")
    @Mapping(source = "status",target = "status")
    List<UserDto> listFrom (List<UserPo> userPoList);

}
