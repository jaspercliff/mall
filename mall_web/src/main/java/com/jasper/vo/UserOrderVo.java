package com.jasper.vo;

import com.jasper.pojo.GoodOrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderVo {
    private Long id;
    private LocalDateTime createTime;
    private BigDecimal payAmount;
    private ArrayList<GoodOrderItem> items;
    private Integer status;
}
