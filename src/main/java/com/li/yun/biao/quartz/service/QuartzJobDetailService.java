package com.li.yun.biao.quartz.service;

import com.li.yun.biao.quartz.entity.QuartzJobDetailEntity;

import java.util.List;


/**
 * @Author liyunbiao
 * @Date : 2020-01-15 16:06
 **/
public interface QuartzJobDetailService {

    /**
     * 查询定时任务实体类
     *
     * @param jobName  job名称
     * @param pageNo   页数
     * @param pageSize 条数
     * @return 任务列表
     */
    List<QuartzJobDetailEntity> getQuartzJobList(String jobName, Integer pageNo, Integer pageSize);

    /**
     * 查总的任务数量
     *
     * @param jobName job名称
     * @return 数量
     */
    Integer listQuartzEntityNum(String jobName);

}
