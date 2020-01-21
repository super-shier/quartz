package com.li.yun.biao.quartz.controller;

import com.li.yun.biao.quartz.common.enums.ErrorCode;
import com.li.yun.biao.quartz.common.response.ApiResponse;
import com.li.yun.biao.quartz.common.response.QuartzJobDetailResponse;
import com.li.yun.biao.quartz.entity.QuartzJobDetailEntity;
import com.li.yun.biao.quartz.service.QuartzJobDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * @Author liyunbiao
 * @Description: 任务控制层
 * @Date : 2020-01-15 16:06
 **/
@RestController
@RequestMapping(value = "/job")
@Api(value = "Quartz任务接口任务", tags = {"Quartz任务接口任务"})
public class JobController {
    private static final Logger logger = LoggerFactory.getLogger(JobController.class);
    /**
     * 加入Qulifier注解，通过名称注入bean
     */
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;
    @Resource
    private QuartzJobDetailService quartzJobDetailService;

    @ApiOperation(value = "新建任务")
    @PostMapping("/add")
    public ApiResponse<Void> addJob(QuartzJobDetailEntity quartz) {
        try {
            if (Objects.nonNull(quartz.getOldJobGroup())) {
                JobKey key = new JobKey(quartz.getOldJobName(), quartz.getOldJobGroup());
                scheduler.deleteJob(key);
            }

            Class cls = Class.forName(quartz.getJobClassName());
            cls.newInstance();

            //构建job信息
            JobDetail job = JobBuilder.newJob(cls).withIdentity(quartz.getJobName(),
                    quartz.getJobGroup()).withDescription(quartz.getDescription()).build();

            //触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + quartz.getJobName(), quartz.getJobGroup())
                    .startNow().withSchedule(cronScheduleBuilder).build();

            //交由Scheduler安排触发
            scheduler.scheduleJob(job, trigger);
            return new ApiResponse<>();
        } catch (Exception e) {
            logger.error("********Exception:{}", e.getLocalizedMessage());
            return new ApiResponse<>(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMsg());
        }
    }

    @ApiOperation(value = "任务列表")
    @PostMapping("/list")
    public ApiResponse<QuartzJobDetailResponse> getJobList(String jobName, @RequestParam Integer pageNo, @RequestParam Integer pageSize) {
        List<QuartzJobDetailEntity> dataList = quartzJobDetailService.getQuartzJobList(jobName, (pageNo - 1) * pageSize, pageSize);
        Integer totalCount = quartzJobDetailService.listQuartzEntityNum(jobName);
        return new ApiResponse<>(new QuartzJobDetailResponse(dataList, totalCount, (totalCount / pageNo) + 1));
    }

    @ApiOperation(value = "触发任务")
    @PostMapping("/trigger")
    public ApiResponse<Void> triggerJob(QuartzJobDetailEntity quartz) {
        try {
            JobKey key = new JobKey(quartz.getJobName(), quartz.getJobGroup());
            scheduler.triggerJob(key);
            return new ApiResponse<>();
        } catch (Exception e) {
            logger.error("********Exception:{}", e.getLocalizedMessage());
            return new ApiResponse<>(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMsg());
        }
    }

    @ApiOperation(value = "停止任务")
    @PostMapping("/pause")
    public ApiResponse<Void> pauseJob(QuartzJobDetailEntity quartz) {
        try {
            JobKey key = new JobKey(quartz.getJobName(), quartz.getJobGroup());
            scheduler.pauseJob(key);
            return new ApiResponse<>();
        } catch (Exception e) {
            logger.error("********Exception:{}", e.getLocalizedMessage());
            return new ApiResponse<>(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMsg());
        }
    }

    @ApiOperation(value = "恢复任务")
    @PostMapping("/resume")
    public ApiResponse<Void> resumeJob(QuartzJobDetailEntity quartz) {
        try {
            JobKey key = new JobKey(quartz.getJobName(), quartz.getJobGroup());
            scheduler.resumeJob(key);
            return new ApiResponse<>();
        } catch (Exception e) {
            logger.error("********Exception:{}", e.getLocalizedMessage());
            return new ApiResponse<>(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMsg());
        }
    }

    @ApiOperation(value = "移除任务")
    @PostMapping("/remove")
    public ApiResponse<Void> removeJob(QuartzJobDetailEntity quartz) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(quartz.getJobName(), quartz.getJobGroup());
            //停止触发器
            scheduler.pauseTrigger(triggerKey);
            //移除触发器
            scheduler.unscheduleJob(triggerKey);
            //删除任务
            scheduler.deleteJob(JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup()));
            logger.info("********removeJob:{}", JobKey.jobKey(quartz.getJobName()));
            return new ApiResponse<>();
        } catch (Exception e) {
            logger.error("********Exception:{}", e.getLocalizedMessage());
            return new ApiResponse<>(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMsg());
        }
    }
}
