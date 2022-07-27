package com.woniu.entity.converter;

import com.github.pagehelper.PageInfo;
import com.woniu.entity.dto.AppointmentDto;
import com.woniu.entity.po.AppointmentPo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentConverter {

    AppointmentDto from(AppointmentPo po);

    List<AppointmentDto> from(List<AppointmentPo> po);

    PageInfo<AppointmentDto> from(PageInfo<AppointmentPo> pageInfoPo);
}
