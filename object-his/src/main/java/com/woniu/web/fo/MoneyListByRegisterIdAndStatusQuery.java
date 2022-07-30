package com.woniu.web.fo;

import com.woniu.config.ResponseResult;
import com.woniu.entity.converter.MoneylistConcerter;
import com.woniu.entity.dto.MoneylistDto;
import com.woniu.entity.po.MoneylistPo;
import com.woniu.repository.MoneyListRepository;
import com.woniu.util.ApplicationContextHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyListByRegisterIdAndStatusQuery {
    private Integer registerId;
    private Integer status;

    public ResponseResult<List<MoneylistDto>> exec() {
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        MoneyListRepository moneyListRepository = applicationContext.getBean(MoneyListRepository.class);
        MoneylistConcerter moneylistConverter = applicationContext.getBean(MoneylistConcerter.class);
        List<MoneylistPo> pos = moneyListRepository.MoneyListByRegisterIdAndStatusQuery(registerId,status);
        List<MoneylistDto> dtos = moneylistConverter.from(pos);
        return new ResponseResult<>(dtos,"ok",2000);

    }
}
