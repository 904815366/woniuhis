package com.woniu.web.fo;

import com.woniu.config.ResponseResult;
import com.woniu.entity.converter.RegisterConverter;
import com.woniu.entity.dto.RegisterDto;
import com.woniu.entity.po.RegisterPo;
import com.woniu.repository.RegisterRepository;
import com.woniu.util.ApplicationContextHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterByIdQuery {
    private Integer id;

    public ResponseResult<RegisterDto> exec(){
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        RegisterRepository repository = applicationContext.getBean(RegisterRepository.class);
        RegisterConverter converter = applicationContext.getBean(RegisterConverter.class);
        RegisterPo po = repository.queryByIdRegister(id);
        RegisterDto dto = converter.from(po);
        return new ResponseResult<>(dto,"ok",2000);
    }
}
