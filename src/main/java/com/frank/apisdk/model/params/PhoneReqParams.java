package com.frank.apisdk.model.params;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Frank
 * @date 2024/7/7
 */
@Data
public class PhoneReqParams implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "手机号码", requiredMode = Schema.RequiredMode.REQUIRED, example = "13571720571")
    @NotEmpty(message = "手机号码不能为空")
    @Pattern(regexp = "^1(3|4|5|6|7|8|9)\\d{9}$", message = "手机号码格式不正确")
    private String phone;
}

