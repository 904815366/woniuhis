package com.woniu.entity.converter;

import com.woniu.entity.dto.NurserecordDto;
import com.woniu.entity.po.NurserecordPo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NurserecrodConcerter {

    NurserecordDto from(NurserecordPo po);

    List<NurserecordDto> from(List<NurserecordPo> po);
}
