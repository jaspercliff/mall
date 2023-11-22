package com.jasper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jasper.pojo.Goods;
import com.jasper.vo.GoodDetailVo;

import java.util.List;

/**
* @author 21903
* @description 针对表【goods(商品信息)】的数据库操作Service
* @createDate 2023-06-23 16:13:16
*/
public interface GoodsService extends IService<Goods> {

    List<Goods> getRecommend();

    GoodDetailVo getGoodDetail(String id);

}
