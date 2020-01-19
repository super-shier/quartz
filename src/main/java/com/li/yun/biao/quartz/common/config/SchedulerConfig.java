package com.li.yun.biao.quartz.common.config;

import com.alibaba.fastjson.JSON;
import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author liyunbiao
 * @Date : 2020-01-15 16:06
 * @Description: 调度数据配置
 **/
@Configuration
public class SchedulerConfig {
    @Resource
    private SpringJobFactory springJobFactory;
    @Value("${quartz.properties}")
    private String quartzProperties;

    @Bean(name = "SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setAutoStartup(true);
        //延时5秒启动
        factory.setStartupDelay(5);
        factory.setQuartzProperties(quartzProperties());
        factory.setJobFactory(springJobFactory);
        return factory;
    }

    /**
     * 读入配置文件(连接数据库)
     */
    @Bean
    public Properties quartzProperties() {
        Properties properties = new Properties();
        properties.putAll(JSON.parseObject(quartzProperties));
        return properties;
    }

    /**
     * quartz初始化监听器
     */
    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }

    /**
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Bean(name = "Scheduler")
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }

}
