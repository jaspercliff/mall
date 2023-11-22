package com.jasper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 用户配置支付请求创建所需要的公共参数
 */
@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {
    private String appId;
    /**
     * 私钥
     */
    private String appPrivateKey;
    /**
     * 支付宝公钥
     */
    private String alipayPublicKey;
    /**
     * 异步通知页面路径
     */
    private String notifyUrl;
    /**
     * 同步通知页面路径
     */
    private String returnUrl;
    /**
     * RSA2
     */
    private String signType;
    private String charset;
    private String gatewayUrl;
    private String logPath;
    private String format;
}
