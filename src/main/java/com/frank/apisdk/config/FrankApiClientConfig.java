package com.frank.apisdk.config;

import com.frank.apisdk.client.FrankApiClient;
import com.frank.apisdk.service.ApiService;
import com.frank.apisdk.service.impl.ApiServiceImpl;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Frank
 * @date 2024/7/3
 */
@Data
@ComponentScan
@Configuration
@ConfigurationProperties("frank.api.client")
public class FrankApiClientConfig {

    /**
     * 访问密钥
     */
    private String accessKey;

    /**
     * 秘密密钥
     */
    private String secretKey;

    /**
     * 网关
     */
    private String host;

    @Bean
    public FrankApiClient qiApiClient() {
        return new FrankApiClient(accessKey, secretKey);
    }

    @Bean
    public ApiService apiService() {
        ApiServiceImpl apiService = new ApiServiceImpl();
        apiService.setFrankApiClient(new FrankApiClient(accessKey, secretKey));
        // if (StringUtils.isNotBlank(host)) {
        //     apiService.setGatewayHost(host);
        // }
        return apiService;
    }
}
