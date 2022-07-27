package com.woniu.entity.converter;

import com.github.pagehelper.PageInfo;
import com.woniu.entity.dto.RegisterDto;
import com.woniu.entity.po.RegisterPo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegisterConverter {

    RegisterDto from(RegisterPo po);

    List<RegisterDto> from(List<RegisterPo> po);

    PageInfo<RegisterDto> from(PageInfo<RegisterPo> pageInfoPo);
}
