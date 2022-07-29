package com.woniu.entity.converter;

import com.woniu.entity.dto.MoneylistDto;
import com.woniu.entity.po.MoneylistPo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MoneylistConcerter {

    MoneylistDto from(MoneylistPo po);

    List<MoneylistDto> from(List<MoneylistPo> po);
}
