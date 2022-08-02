package com.woniu.web.fo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyStatusAndRegisterMoneysComment {

    private Integer registerid;
    private String status;
    private String sumMoney;
    private String ids;
}
