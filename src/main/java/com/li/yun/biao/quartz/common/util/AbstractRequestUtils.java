package com.li.yun.biao.quartz.common.util;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @Author: liyunbiao
 * @Date: 2019/7/24 12:37 PM
 * @description 多次读取时流丢失处理
 */
public abstract class AbstractRequestUtils {
    private static Logger logger = LoggerFactory.getLogger(AbstractRequestUtils.class);

    private AbstractRequestUtils() {
    }

    public static String getRequestBody(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        if (Lists.newArrayList("POST", "PUT").contains(request.getMethod())) {
            try (BufferedReader bufferedReader = request.getReader()) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return sb.toString();
    }
}
