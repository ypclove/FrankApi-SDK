package com.frank.apisdk.utils;

import cn.hutool.crypto.digest.DigestUtil;

/**
 * 签名工具类
 *
 * @author Frank
 * @date 2024/7/3
 */
public class SignUtil {

    /**
     * 获取签名值
     *
     * @param body      请求体
     * @param secretKey 秘钥
     * @return 签名值
     */
    public static String getSign(String body, String secretKey) {
        return DigestUtil.sha256Hex(body + "." + secretKey);
    }
}
