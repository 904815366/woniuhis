package com.woniu.web.fo;

import com.woniu.config.ResponseResult;
import com.woniu.entity.po.RegisterPo;
import com.woniu.service.RegisterService;
import com.woniu.util.ApplicationContextHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyMoneyListOfStatusComment {
  private String outIds;
  private String returnIds;

}
