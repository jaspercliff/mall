package com.jasper.dto;

import com.jasper.pojo.GoodOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class OrderDTO extends GoodOrder {
    private Integer check;
    private ArrayList<Long> orderIds;
}
