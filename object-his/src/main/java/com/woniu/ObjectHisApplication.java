package com.woniu;

import com.google.common.eventbus.EventBus;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.woniu.mapper")
public class ObjectHisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectHisApplication.class, args);
    }
    @Bean
    public EventBus eventBus() {
        EventBus eventBus = new EventBus();
        return eventBus;
    }
}
