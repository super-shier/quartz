package com.li.yun.biao.quartz.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.li.yun.biao.quartz.common.enums.ErrorCode;
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


    public static ApiResponse error(String errorCode, String errorMsg) {
        ApiResponse response = new ApiResponse();
        response.setErrorCode(errorCode);
        response.setErrorMsg(errorMsg);
        return response;
    }

    public static ApiResponse success() {
        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        return response;
    }

    public static ApiResponse success(Object data) {
        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static ApiResponse error(ErrorCode errorCode) {
        ApiResponse response = new ApiResponse();
        response.setErrorCode(errorCode.getCode());
        response.setErrorMsg(errorCode.getMsg());
        return response;
    }
}
