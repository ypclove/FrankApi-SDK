package com.frank.apisdk.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 通用返回工具类
 *
 * @author Frank
 * @date 2024/06/22
 */
@Slf4j
@RestControllerAdvice
public class MyGlobalExceptionHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler(ApiException.class)
    public ErrorResponse<?> businessExceptionHandler(ApiException e) {
        log.error("全局异常错误消息：{}", e.getMessage());
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse<?> handleValidationExceptions(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        log.error("参数校验失败: {}", msg);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR.getCode(), msg);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse<?> handleConstraintViolationException(ConstraintViolationException e) {
        String msg = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR.getCode(), msg);
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("运行时异常：{}", e.getMessage());
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR.getCode(), e.getMessage());
    }
}
