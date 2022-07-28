package com.woniu.entity.converter;

import com.woniu.entity.dto.BedDto;
import com.woniu.entity.dto.MoneyrecordDto;
import com.woniu.entity.po.BedPo;
import com.woniu.entity.po.MoneyrecordPo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MoneyrecordConverter {

    MoneyrecordDto from(MoneyrecordPo po);

    List<MoneyrecordDto> from(List<MoneyrecordPo> pos);

}
