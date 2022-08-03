package com.woniu;

import com.google.common.eventbus.EventBus;
import com.woniu.util.MinioUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@MapperScan("com.woniu.mapper")
@EnableScheduling
public class ObjectHisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectHisApplication.class, args);
    }
    @Bean
    public EventBus eventBus() {
        EventBus eventBus = new EventBus();
        return eventBus;
    }

//    @PostConstruct
//    void started(){
//        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
//    }

    @Bean
    public MinioUtils minioUtils() {
        return new MinioUtils("http://127.0.0.1:9000", "minioadmin", "minioadmin");
    }
}
