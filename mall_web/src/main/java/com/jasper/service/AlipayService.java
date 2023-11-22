package com.jasper.service;

import com.alipay.api.AlipayApiException;
import com.jasper.config.Alipay;

public interface AlipayService {
    String pay(Alipay alipay) throws AlipayApiException;
}
