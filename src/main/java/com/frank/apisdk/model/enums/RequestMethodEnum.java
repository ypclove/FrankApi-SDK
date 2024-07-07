package com.frank.apisdk.model.enums;

import com.frank.apisdk.exception.ApiException;
import com.frank.apisdk.exception.ErrorCode;
import lombok.Getter;

/**
 * @author Frank
 * @date 2024/7/6
 */
@Getter
public enum RequestMethodEnum {

    /**
     * GET请求
     */
    GET(1, "GET"),
    POST(2, "POST");

    private final int code;
    private final String text;

    RequestMethodEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    /**
     * 根据 code 获取 text
     *
     * @param code 枚举 code
     * @return 枚举 text
     */
    public static String getTextByCode(int code) {
        for (RequestMethodEnum method : values()) {
            if (method.getCode() == code) {
                return method.getText();
            }
        }
        throw new ApiException(ErrorCode.PARAMS_ERROR, "请求方法不存在");
    }
}
