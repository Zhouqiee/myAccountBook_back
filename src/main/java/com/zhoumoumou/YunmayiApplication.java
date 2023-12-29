package com.zhoumoumou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan("com.zhoumoumou.yunmayi.dao")
@EnableScheduling
public class YunmayiApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunmayiApplication.class, args);
    }

}
