package com.frank.apisdk.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Frank
 * @date 2024/7/3
 */
@Data
public class ErrorResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 全参构造器
     *
     * @param code 状态码
     * @param data 响应数据
     * @param msg  提示消息
     */
    public ErrorResponse(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}


