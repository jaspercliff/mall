package com.jasper.service.impl;

import cn.hutool.json.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.jasper.config.Alipay;
import com.jasper.config.AlipayConfig;
import com.jasper.service.AlipayService;
import com.jasper.service.GoodOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlipayServiceImpl implements AlipayService {

    private final AlipayConfig alipayConfig;
    private final GoodOrderService orderService;
    @Override
    public String pay(Alipay alipay) throws AlipayApiException {
//        设置支付回调中可以在request获取的参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOnce("out_trade_no",alipay.getTradeId());
        jsonObject.putOnce("total_amount", alipay.getTotalAmount());
        jsonObject.putOnce("subject", alipay.getSubject());
        String params = jsonObject.toString();
//设置支付参数
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setBody(params);
        return getQrCode(model);
    }


    public String getQrCode(AlipayTradePrecreateModel model) throws AlipayApiException {
        AlipayClient alipayClient = getAlipayClient();
        AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();
//        设置请求参数
        alipayRequest.setBizModel(model);
        alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());
        AlipayTradePrecreateResponse response = null;
        response = alipayClient.execute(alipayRequest);
        String body = response.getBody();
        log.info("请求的二维码信息=>>>"+body);
        return response.getQrCode();
    }

    /**
     * 获取阿里客户端
     * @return
     */
    public AlipayClient getAlipayClient() {
        return new DefaultAlipayClient(
                alipayConfig.getGatewayUrl(),
                alipayConfig.getAppId(),
                alipayConfig.getAppPrivateKey(),
                alipayConfig.getFormat(),
                alipayConfig.getCharset(),
                alipayConfig.getAlipayPublicKey(),
                alipayConfig.getSignType()
        );
    }

}
