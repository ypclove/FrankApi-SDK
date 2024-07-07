package com.frank.apisdk.service;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.frank.apisdk.client.FrankApiClient;
import com.frank.apisdk.exception.ApiException;
import com.frank.apisdk.exception.ErrorCode;
import com.frank.apisdk.exception.ErrorResponse;
import com.frank.apisdk.model.enums.RequestMethodEnum;
import com.frank.apisdk.model.request.BaseRequest;
import com.frank.apisdk.model.response.ResultResponse;
import com.frank.apisdk.utils.SignUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Frank
 * @date 2024/7/6
 */
@Data
@Slf4j
public abstract class BaseService implements ApiService {

    private FrankApiClient frankApiClient;

    @Override
    public <O, T extends ResultResponse> T request(BaseRequest<O, T> request) throws ApiException {
        try {
            return res(request);
        } catch (Exception e) {
            throw new ApiException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
    }

    @Override
    public <O, T extends ResultResponse> T request(FrankApiClient frankApiClient, BaseRequest<O, T> request) throws ApiException {
        checkConfig(frankApiClient);
        return request(request);
    }

    /**
     * 检查配置
     *
     * @param frankApiClient frankApi 客户端
     * @throws ApiException ApiException
     */
    private void checkConfig(FrankApiClient frankApiClient) throws ApiException {
        if (Objects.isNull(frankApiClient) && Objects.isNull(this.getFrankApiClient())) {
            throw new ApiException(ErrorCode.NO_AUTH_ERROR, "请先配置 AccessKey 和 SecretKey");
        }
        if (Objects.nonNull(this.getFrankApiClient()) && !StringUtils.isAnyBlank(frankApiClient.getAccessKey(), frankApiClient.getSecretKey())) {
            this.setFrankApiClient(frankApiClient);
        }
    }

    /**
     * 执行请求
     *
     * @param request request
     * @return {@link HttpResponse}
     * @throws ApiException ApiException
     */
    private <O, T extends ResultResponse> HttpResponse doRequest(BaseRequest<O, T> request) throws ApiException {
        try {
            return getHttpRequestByRequestMethod(request).execute();
        } catch (Exception e) {
            throw new ApiException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
    }

    /**
     * 拼接 Get 请求
     *
     * @param request request
     * @param path    请求路径
     * @return GET 请求完整路径
     */
    private <O, T extends ResultResponse> String splicingGetRequest(BaseRequest<O, T> request, String path) {
        // TODO: 网关
        // StringBuilder urlBuilder = new StringBuilder(gatewayHost);
        StringBuilder urlBuilder = new StringBuilder();
        // urlBuilder最后是 / 结尾，并且 path 以 / 开头的情况下，去掉 urlBuilder 结尾的 /
        if (urlBuilder.toString().endsWith("/") && path.startsWith("/")) {
            urlBuilder.setLength(urlBuilder.length() - 1);
        }
        urlBuilder.append(path);
        if (!request.getRequestParams().isEmpty()) {
            urlBuilder.append("?");
            for (Map.Entry<String, Object> entry : request.getRequestParams().entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().toString();
                urlBuilder.append(key).append("=").append(value).append("&");
            }
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        }
        log.info("完整请求路径：{}", urlBuilder);
        return urlBuilder.toString();
    }

    /**
     * 获取请求头
     *
     * @param body           请求体
     * @param frankApiClient frankApi 客户端
     * @return 封装的请求头
     */
    private Map<String, String> getHeaders(String body, FrankApiClient frankApiClient) {
        Map<String, String> hashMap = new HashMap<>(4);
        hashMap.put("accessKey", frankApiClient.getAccessKey());
        String encodedBody = SecureUtil.md5(body);
        hashMap.put("body", encodedBody);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", SignUtil.getSign(encodedBody, frankApiClient.getSecretKey()));
        log.info("请求头");
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            log.info("{}: {}", entry.getKey(), entry.getValue());
        }
        return hashMap;
    }

    /**
     * 通过请求方法获取 http 响应
     *
     * @param request request
     * @return {@link HttpResponse}
     * @throws ApiException ApiException
     */
    private <O, T extends ResultResponse> HttpRequest getHttpRequestByRequestMethod(BaseRequest<O, T> request) throws ApiException {
        if (ObjectUtils.isEmpty(request)) {
            throw new ApiException(ErrorCode.OPERATION_ERROR, "请求参数错误");
        }
        String path = request.getPath().trim();
        Integer method = request.getMethod();

        if (ObjectUtils.isEmpty(method)) {
            throw new ApiException(ErrorCode.OPERATION_ERROR, "请求方法不存在");
        }
        if (StringUtils.isBlank(path)) {
            throw new ApiException(ErrorCode.OPERATION_ERROR, "请求路径不存在");
        }
        // TODO: 网关
        // if (path.startsWith(gatewayHost)) {
        //     path = path.substring(gatewayHost.length());
        // }
        log.info("请求方法：{}", RequestMethodEnum.getTextByCode(method));
        log.info("请求路径：{}", path);
        log.info("请求参数：{}", request.getRequestParams());
        HttpRequest httpRequest;
        switch (method) {
            case 1: {
                httpRequest = HttpRequest.get(splicingGetRequest(request, path));
                break;
            }
            case 2: {
                // TODO: 网关
                // httpRequest = HttpRequest.post(gatewayHost + path);
                httpRequest = HttpRequest.post(path);
                break;
            }
            default: {
                throw new ApiException(ErrorCode.OPERATION_ERROR, "不支持该请求");
            }
        }
        return httpRequest
                .addHeaders(getHeaders(JSONUtil.toJsonStr(request), frankApiClient))
                .body(JSONUtil.toJsonStr(request.getRequestParams()));
    }

    /**
     * 获取响应数据
     *
     * @param request request
     * @return 响应数据
     * @throws ApiException ApiException
     */
    private <O, T extends ResultResponse> T res(BaseRequest<O, T> request) throws ApiException {
        if (Objects.isNull(frankApiClient) || StringUtils.isAnyBlank(frankApiClient.getAccessKey(), frankApiClient.getSecretKey())) {
            throw new ApiException(ErrorCode.NO_AUTH_ERROR, "请先配置 accessKey 和 SecretKey");
        }
        T rsp;
        try {
            Class<T> clazz = request.getResponseClass();
            rsp = clazz.newInstance();
        } catch (Exception e) {
            throw new ApiException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
        HttpResponse httpResponse = doRequest(request);
        String body = httpResponse.body();
        Map<String, Object> data = new HashMap<>();
        if (httpResponse.getStatus() != 200) {
            ErrorResponse errorResponse = JSONUtil.toBean(body, ErrorResponse.class);
            throw new ApiException(ErrorCode.PARAMS_ERROR, errorResponse.getMsg());
        } else {
            try {
                // 尝试解析为 JSON 对象
                data = new Gson().fromJson(body, new TypeToken<Map<String, Object>>() {
                }.getType());
            } catch (JsonSyntaxException e) {
                // 解析失败，将 body 作为普通字符串处理
                data.put("value", body);
            }
        }
        rsp.setData(data);
        return rsp;
    }
}
