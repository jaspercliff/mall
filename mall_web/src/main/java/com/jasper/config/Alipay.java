package com.jasper.config;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Alipay {
    /**
     * 订单号
     */
    private String tradeId;
    /**
     * 付款金额
     */
    private BigDecimal totalAmount;
    /**
     * 订单名称
     */
    private String subject;
    private String alipayTraceNo;
}
