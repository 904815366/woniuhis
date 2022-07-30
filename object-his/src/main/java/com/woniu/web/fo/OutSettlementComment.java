package com.woniu.web.fo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutSettlementComment {

    private Integer registerId;
    private Integer num;
    private Double sumMoney;
    private String moneyListId;
}
