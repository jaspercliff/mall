package com.jasper.dto;

import com.jasper.pojo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDTO extends Goods {
    private List<GoodsMemberPrice> memberPrices;
    private List<GoodsFullReduction> goodsFullReductions;
    private List<GoodsLadder> ladders;
    private List<GoodsSkuStock> goodsSkuStocks;
    private List<GoodsAttributeValue> attributeValues;
}
