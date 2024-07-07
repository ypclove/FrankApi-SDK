package com.frank.apisdk.exception;

/**
 * 通用返回工具类
 *
 * @author Frank
 * @date 2024/06/22
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data 成功数据
     * @param <T>  数据类型
     * @return ok
     */
    public static <T> ErrorResponse<T> success(T data) {
        return new ErrorResponse<>(ErrorCode.SUCCESS.getCode(), data, ErrorCode.SUCCESS.getMsg());
    }

    /**
     * 失败
     *
     * @param code 状态码
     * @param msg  错误消息
     * @param <T>  数据类型
     * @return error
     */
    public static <T> ErrorResponse<T> error(int code, String msg) {
        return new ErrorResponse<>(code, null, msg);
    }

    /**
     * 失败
     *
     * @param code 状态码
     * @param data 数据
     * @param msg  错误消息
     * @param <T>  数据类型
     * @return error
     */
    public static <T> ErrorResponse<T> error(int code, T data, String msg) {
        return new ErrorResponse<>(code, data, msg);
    }
}
