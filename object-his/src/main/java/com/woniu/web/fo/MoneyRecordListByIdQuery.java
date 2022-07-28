package com.woniu.web.fo;

import com.woniu.config.ResponseResult;
import com.woniu.entity.converter.MoneyrecordConverter;
import com.woniu.entity.converter.RegisterConverter;
import com.woniu.entity.dto.MoneyrecordDto;
import com.woniu.entity.po.MoneyrecordPo;
import com.woniu.repository.MoneyrecordRepository;
import com.woniu.repository.RegisterRepository;
import com.woniu.util.ApplicationContextHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyRecordListByIdQuery {
    private Integer id;

    public ResponseResult<List<MoneyrecordDto>> exec() {
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        MoneyrecordRepository moneyrecordRepository = applicationContext.getBean(MoneyrecordRepository.class);
        MoneyrecordConverter converter = applicationContext.getBean(MoneyrecordConverter.class);
        List<MoneyrecordPo> pos = moneyrecordRepository.queryListById(id);
        List<MoneyrecordDto> dtos = converter.from(pos);
        return new ResponseResult<>(dtos,"ok",2000);
    }
}
