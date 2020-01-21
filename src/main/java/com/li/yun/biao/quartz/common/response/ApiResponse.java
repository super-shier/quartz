package com.li.yun.biao.quartz.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liyunbiao
 * @Date: 2019/7/24 4:11 PM
 * @description
 */
@Data
@ApiModel(value = "请求响应")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Serializable {
    @ApiModelProperty(value = "请求状态")
    private boolean success;
    @ApiModelProperty(value = "响应交易码")
    private String errorCode;
    @ApiModelProperty(value = "响应交易")
    private String errorMsg;
    @ApiModelProperty(value = "响应数据")
    private T data;

    public ApiResponse() {
        this.success = true;
    }

    public ApiResponse(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ApiResponse(T data) {
        this.success = true;
        this.data = data;
    }
}
