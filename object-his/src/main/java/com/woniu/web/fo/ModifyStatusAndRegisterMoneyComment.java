package com.woniu.web.fo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModifyStatusAndRegisterMoneyComment {
    private Integer id;
    private Integer registerid;
    private String status;
    private String consummoney;
}
