package com.li.yun.biao.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * @Author liyunbiao
 * @Date : 2020-01-15 16:06
 * @Description: Simon创建的任务
 **/
public class BobSimonPlayJob implements Job {

    /**
     * job执行的内容
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        /**
         *  怎么执行这里面的任务呢哈
         */
        System.out.println("Bob Simon is an awesome man in the world!");
    }

}
