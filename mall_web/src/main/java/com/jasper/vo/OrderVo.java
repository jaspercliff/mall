package com.jasper.vo;

import com.jasper.pojo.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVo {
    private BigDecimal payAmount;
    private Integer delayTime;
    private String address;
    private ArrayList<Goods> goodList;
}
