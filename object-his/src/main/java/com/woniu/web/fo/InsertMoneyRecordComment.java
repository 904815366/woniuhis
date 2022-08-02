package com.woniu.web.fo;

import com.woniu.config.ResponseResult;
import com.woniu.entity.converter.RegisterConverter;
import com.woniu.entity.po.MoneyrecordPo;
import com.woniu.mapper.mysql.RegisterMysqlDao;
import com.woniu.service.MoneyrecordService;
import com.woniu.util.ApplicationContextHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertMoneyRecordComment {

    private Integer id;
    private Integer registerid;
    private Double prepaymoney;
    private LocalDateTime prepaytime;
    private Integer userid;
    private String type;

}
