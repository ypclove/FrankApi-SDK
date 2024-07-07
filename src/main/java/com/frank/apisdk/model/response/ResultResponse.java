package com.frank.apisdk.model.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回类
 *
 * @author Frank
 * @date 2024/7/3
 */
@Setter
public class ResultResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> data = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getData() {
        return data;
    }
}
