package com.jasper.vo;

import com.jasper.pojo.UserReceiveAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmOrderVo {
    private Integer productTotal;
    private BigDecimal priceTotal;
    private BigDecimal freightTotal;
    private BigDecimal payActual;
    private Integer integration;
    private List<UserReceiveAddress> addressList;
}
