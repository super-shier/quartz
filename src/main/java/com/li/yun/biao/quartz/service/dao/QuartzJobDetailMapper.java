package com.li.yun.biao.quartz.service.dao;

import com.li.yun.biao.quartz.entity.QuartzJobDetailEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author liyunbiao
 * @Date : 2020-01-15 16:06
 * @Description:
 **/
public interface QuartzJobDetailMapper {

    /**
     * 计算job的个数
     *
     * @param jobName 任务名称
     * @return 任务数量
     */
    int getQuartzJobCount(@Param("jobName") String jobName);

    /**
     * 查询出任务
     *
     * @param jobName   任务名称
     * @param pageStart start
     * @param pageEnd   limit
     * @return 任务列表
     */
    List<QuartzJobDetailEntity> getQuartzJobList(@Param("jobName") String jobName, @Param("pageStart") Integer pageStart, @Param("pageEnd") Integer pageEnd);
}
