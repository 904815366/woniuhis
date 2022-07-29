package com.woniu.web.fo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 修改入院信息表的余额
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyRegisterMoneyComment {
    private Integer registerid;
    private Double prepaymoney;
}
