package com.frank.apisdk.model.request;

import com.frank.apisdk.model.enums.RequestMethodEnum;
import com.frank.apisdk.model.params.IpReqParams;
import com.frank.apisdk.model.response.ResultResponse;

import static com.frank.apisdk.constant.SDKConstant.IP_INFO_PATH;

/**
 * @author Frank
 * @date 2024/7/6
 */
public class IpInfoRequest extends BaseRequest<IpReqParams, ResultResponse> {

    @Override
    public Integer getMethod() {
        return RequestMethodEnum.GET.getCode();
    }

    @Override
    public String getPath() {
        return IP_INFO_PATH;
    }

    @Override
    public Class<ResultResponse> getResponseClass() {
        return ResultResponse.class;
    }
}
