package com.frank.apisdk.model.request;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.frank.apisdk.model.response.ResultResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Frank
 * @date 2024/7/6
 */
public abstract class BaseRequest<O, T extends ResultResponse> {

    private Map<String, Object> requestParams = new HashMap<>();

    /**
     * 获取请求方法
     *
     * @return 请求方法
     */
    public abstract Integer getMethod();

    /**
     * 获取请求路径
     *
     * @return 请求路径
     */
    public abstract String getPath();

    /**
     * 获取响应类
     *
     * @return 响应类
     */
    public abstract Class<T> getResponseClass();

    @JsonAnyGetter
    public Map<String, Object> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(O params) {
        this.requestParams = new Gson().fromJson(JSONUtil.toJsonStr(params), new TypeToken<Map<String, Object>>() {
        }.getType());
    }
}