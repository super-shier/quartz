package com.li.yun.biao.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author liyunbiao
 * @Date : 2020-01-15 16:06
 * @Description:
 **/

@MapperScan("com.li.yun.biao.quartz.service.dao")
@SpringBootApplication
public class QuartzApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }
}
