package com.frank.apisdk.model.request;

import com.frank.apisdk.model.response.ResultResponse;
import lombok.Setter;

/**
 * @author Frank
 * @date 2024/7/6
 */
@Setter
public class CurrencyRequest extends BaseRequest<Object, ResultResponse> {

    private Integer method;

    private String path;

    @Override
    public Integer getMethod() {
        return method;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Class<ResultResponse> getResponseClass() {
        return ResultResponse.class;
    }
}
