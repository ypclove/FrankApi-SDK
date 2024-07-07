package com.frank.apisdk.validator;


import com.frank.apisdk.annotation.CheckIpAddress;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.frank.apisdk.constant.SDKConstant.IP_MAX_LEN;


/**
 * @author Frank
 * @date 2024/7/4
 */
public class IpAddressValidator implements ConstraintValidator<CheckIpAddress, String> {

    @Override
    public void initialize(CheckIpAddress constraintAnnotation) {
    }

    @Override
    public boolean isValid(String ip, ConstraintValidatorContext context) {
        // 如果没有输入，不进行校验，放行
        if (ip == null || ip.isEmpty()) {
            return true;
        }
        // 如果输入了 ip，进行校验
        if (ip.length() > IP_MAX_LEN) {
            return false;
        }
        if (!ip.contains(".")) {
            return false;
        }
        String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            return false;
        }
        try {
            for (String part : parts) {
                int number = Integer.parseInt(part);
                if ((number < 0) || (number > 255)) {
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}