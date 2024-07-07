package com.frank.apisdk.annotation;


import com.frank.apisdk.validator.IpAddressValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Frank
 * @date 2024/7/4
 */
@Constraint(validatedBy = IpAddressValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckIpAddress {

    String message() default "IP 地址无效";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
