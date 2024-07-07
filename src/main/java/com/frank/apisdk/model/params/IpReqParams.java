package com.frank.apisdk.model.params;

import com.frank.apisdk.annotation.CheckIpAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * @author Frank
 * @date 2024/7/4
 */
@Data
@Validated
public class IpReqParams implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "IP 地址", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "255.255.255.255")
    @CheckIpAddress(message = "IP 地址无效")
    private String ip;
}
