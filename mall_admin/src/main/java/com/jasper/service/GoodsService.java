package com.jasper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jasper.common_utils.R;
import com.jasper.dto.GoodsDTO;
import com.jasper.dto.GoodsSearchDTO;
import com.jasper.pojo.Goods;

/**
* @author 21903
* @description 针对表【goods(商品信息)】的数据库操作Service
* @createDate 2023-07-27 17:38:26
*/
public interface GoodsService extends IService<Goods> {

    Page<Goods> pageGoodsList(GoodsSearchDTO goodsSearchDTO);

    R<?> addGoods(GoodsDTO goodsDTO);

    GoodsDTO getGoodById(String id);

    Boolean deleteGoods(Long id);
}
