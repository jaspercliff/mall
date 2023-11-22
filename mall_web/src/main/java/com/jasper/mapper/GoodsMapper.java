package com.jasper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasper.pojo.Goods;
import org.apache.ibatis.annotations.Update;

/**
* @author 21903
* @description 针对表【goods(商品信息)】的数据库操作Mapper
* @createDate 2023-06-22 16:01:54
* @Entity com.com.mapper.pojo.Goods
*/
public interface GoodsMapper extends BaseMapper<Goods> {

    @Update("update goods set stock=stock-${quantity} where id = ${id} and stock>0 ")
    int updateStock(Long id,Integer quantity);
}




