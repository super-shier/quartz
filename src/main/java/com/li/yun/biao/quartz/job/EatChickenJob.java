package com.li.yun.biao.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.Serializable;


/**
 * @Author liyunbiao
 * @Date : 2020-01-15 16:06
 * @Description: 吃鸡任务
 **/
public class EatChickenJob implements Job, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * job执行的内容
     *
     * @param arg0
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println("大吉大利、今晚吃鸡");
    }

}
