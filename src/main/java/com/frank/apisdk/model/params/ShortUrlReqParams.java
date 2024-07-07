package com.frank.apisdk.model.params;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Frank
 * @date 2024/7/7
 */
@Data
public class ShortUrlReqParams implements Serializable {

    private static final long serialVersionUID = 1L;

    private String url;
}
