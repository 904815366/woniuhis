package com.woniu.entity.converter;

import com.woniu.entity.dto.BedDto;
import com.woniu.entity.po.BedPo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BedConverter {

    BedDto from(BedPo po);

    List<BedDto> from(List<BedPo> po);

}
