package com.jasper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.mapper.GoodsSkuStockMapper;
import com.jasper.pojo.GoodsSkuStock;
import com.jasper.service.GoodsSkuStockService;
import org.springframework.stereotype.Service;

/**
* @author 21903
* @description 针对表【goods_sku_stock(sku的库存)】的数据库操作Service实现
* @createDate 2023-08-04 15:13:35
*/
@Service
public class GoodsSkuStockServiceImpl extends ServiceImpl<GoodsSkuStockMapper, GoodsSkuStock>
    implements GoodsSkuStockService{

}




