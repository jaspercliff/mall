package com.jasper.vo;

import com.jasper.pojo.Goods;
import com.jasper.pojo.GoodsAttributeValue;
import com.jasper.pojo.GoodsSkuStock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodDetailVo extends Goods {
        private List<GoodsSkuStock> goodsSkuStocks;
        private List<GoodsAttributeValue> attributeValues;
    }
