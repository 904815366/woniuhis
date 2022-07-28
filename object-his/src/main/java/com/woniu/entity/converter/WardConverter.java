package com.woniu.entity.converter;

import com.woniu.entity.dto.WardDto;
import com.woniu.entity.po.WardPo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WardConverter {

    WardDto from(WardPo po);

    List<WardDto> from(List<WardPo> po);

}
