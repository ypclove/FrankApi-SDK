package com.frank.apisdk.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * FrankApi 客户端
 *
 * @author Frank
 * @date 2024/7/3
 */
@Data
@Slf4j
@AllArgsConstructor
public class FrankApiClient {

    /**
     * accessKey
     */
    private String accessKey;

    /**
     * secretKey
     */
    private String secretKey;
}
