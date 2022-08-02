package com.woniu.config;

import com.woniu.service.MoneylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimeTask {

    @Autowired
    private MoneylistService moneylistService;

    @Scheduled(cron = "0 */60 * * * ?")
    public void addEverydayMoneyList() {
        System.out.println("执行了addEverydayMoneyList");
        moneylistService.addEverydayMoneyList();
    }


}
