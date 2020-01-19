package com.li.yun.biao.quartz.common.response;

import com.li.yun.biao.quartz.entity.QuartzJobDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: liyunbiao
 * @date: 2020/1/19 6:11 下午
 * @description
 */
@Data
@AllArgsConstructor
public class QuartzJobDetailResponse implements Serializable {
    private List<QuartzJobDetailEntity> dataList;
    private Integer dataCount;
    private Integer totalPage;

    public QuartzJobDetailResponse() {
    }
}
