package com.jasper.controller;

import com.alipay.api.AlipayApiException;
import com.jasper.config.Alipay;
import com.jasper.service.AlipayService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webService/alipay")
public class AliPayController {
    @Resource
    private AlipayService alipayService;
    @GetMapping("/pay")
    public String pay(Alipay alipay) throws AlipayApiException {
        return alipayService.pay(alipay);
    }
}
