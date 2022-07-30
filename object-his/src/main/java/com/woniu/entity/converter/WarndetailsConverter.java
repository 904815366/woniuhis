package com.woniu.entity.converter;

import com.woniu.entity.dto.WarndetailsDto;
import com.woniu.entity.po.WarndetailsPo;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel="spring")
public interface WarndetailsConverter {
    WarndetailsDto con(WarndetailsPo po);
    List<WarndetailsDto> listFrom (List<WarndetailsPo> warndetailsPoList);
}
