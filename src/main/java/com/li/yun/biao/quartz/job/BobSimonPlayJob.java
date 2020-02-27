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
 * @Description: Simon创建的任务
 **/
public class BobSimonPlayJob implements Job, Serializable {
    private static final Logger logger = LoggerFactory.getLogger(BobSimonPlayJob.class);

    /**
     * job执行的内容
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("******Bob Simon is an awesome man in the world!");
    }

}
