package com.woniu.web.fo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutSettlementComment {

    private Integer registerId;
    private Integer notNum;
    private Double notMoney;
    private String notMoneyListId;

    private Integer returnNum;
    private Double returnMoney;
    private String returnMoneyListId;

}
