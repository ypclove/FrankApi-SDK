package com.frank.apisdk.exception;

import lombok.Getter;

/**
 * @author Frank
 * @date 2024/7/3
 */
@Getter
public class ApiException extends RuntimeException {

    /**
     * 状态码
     */
    private final int code;

    public ApiException(ErrorCode code) {
        super(code.getMsg());
        this.code = code.getCode();
    }

    public ApiException(ErrorCode code, String message) {
        super(message);
        this.code = code.getCode();
    }
}
