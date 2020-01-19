package com.li.yun.biao.quartz.service.impl;

import com.li.yun.biao.quartz.entity.QuartzJobDetailEntity;
import com.li.yun.biao.quartz.service.QuartzJobDetailService;
import com.li.yun.biao.quartz.service.dao.QuartzJobDetailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Author liyunbiao
 * @Date : 2020-01-15 16:06
 **/
@Service
public class JobDetailServiceImpl implements QuartzJobDetailService {

    @Resource
    QuartzJobDetailMapper quartzJobDetailMapper;


    @Override
    public List<QuartzJobDetailEntity> getQuartzJobList(String jobName, Integer pageNo, Integer pageSize) {
        return quartzJobDetailMapper.getQuartzJobList(jobName, pageNo, pageSize);
    }

    @Override
    public Integer listQuartzEntityNum(String jobName) {
        return quartzJobDetailMapper.getQuartzJobCount(jobName);
    }
}
