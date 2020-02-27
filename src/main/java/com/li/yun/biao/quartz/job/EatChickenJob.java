package com.li.yun.biao.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;


/**
 * @Author liyunbiao
 * @Date : 2020-01-15 16:06
 * @Description: 吃鸡任务
 **/
public class EatChickenJob implements Job, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(EatChickenJob.class);

    /**
     * job执行的内容
     *
     * @param arg0
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        logger.info("******大吉大利、今晚吃鸡");
    }

}
