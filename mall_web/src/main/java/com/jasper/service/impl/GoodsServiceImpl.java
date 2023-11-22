package com.jasper.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.mapper.GoodsMapper;
import com.jasper.pojo.Goods;
import com.jasper.pojo.GoodsAttributeValue;
import com.jasper.pojo.GoodsSkuStock;
import com.jasper.service.GoodsAttributeValueService;
import com.jasper.service.GoodsService;
import com.jasper.service.GoodsSkuStockService;
import com.jasper.vo.GoodDetailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 21903
* @description 针对表【goods(商品信息)】的数据库操作Service实现
* @createDate 2023-06-23 16:13:16
*/
@Service
@RequiredArgsConstructor
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService{
    private final GoodsAttributeValueService attributeValueService;
    private final GoodsSkuStockService skuStockService;
    @Override
    public List<Goods> getRecommend() {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getIsRecommend,1);
        return super.list(wrapper);
    }

    @Override
    public GoodDetailVo getGoodDetail(String id) {
        //获取商品信息
        Goods goods = super.getById(id);
        GoodDetailVo goodDetailVo = BeanUtil.copyProperties(goods, GoodDetailVo.class);
//
        LambdaQueryWrapper<GoodsAttributeValue> wrapper =
                new LambdaQueryWrapper<GoodsAttributeValue>().
                        eq(GoodsAttributeValue::getProductId, id);
        List<GoodsAttributeValue> attributeValueList = attributeValueService.list(wrapper);
        goodDetailVo.setAttributeValues(attributeValueList);
        LambdaQueryWrapper<GoodsSkuStock> eq = new LambdaQueryWrapper<GoodsSkuStock>().eq(GoodsSkuStock::getProductId, id);
        List<GoodsSkuStock> skuStockList = skuStockService.list(eq);
        goodDetailVo.setGoodsSkuStocks(skuStockList);
        return goodDetailVo;
    }
}




