package com.frank.apisdk.service;

import cn.hutool.http.HttpResponse;
import com.frank.apisdk.client.FrankApiClient;
import com.frank.apisdk.exception.ApiException;
import com.frank.apisdk.model.request.BaseRequest;
import com.frank.apisdk.model.response.ResultResponse;

/**
 * @author Frank
 * @date 2024/7/6
 */
public interface ApiService {

    /**
     * 通用请求
     *
     * @param request request
     * @return {@link HttpResponse}
     * @throws ApiException ApiException
     */

    <O, T extends ResultResponse> T request(BaseRequest<O, T> request) throws ApiException;

    /**
     * 通用请求
     *
     * @param frankApiClient frankApi 客户端
     * @param request        request
     * @return {@link T}
     * @throws ApiException ApiException
     */
    <O, T extends ResultResponse> T request(FrankApiClient frankApiClient, BaseRequest<O, T> request) throws ApiException;
}
