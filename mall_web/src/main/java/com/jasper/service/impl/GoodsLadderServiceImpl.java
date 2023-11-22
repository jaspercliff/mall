package com.jasper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.mapper.GoodsLadderMapper;
import com.jasper.pojo.GoodsLadder;
import com.jasper.service.GoodsLadderService;
import org.springframework.stereotype.Service;

/**
* @author 21903
* @description 针对表【goods_ladder(产品阶梯价格表(只针对同商品))】的数据库操作Service实现
* @createDate 2023-08-04 15:13:35
*/
@Service
public class GoodsLadderServiceImpl extends ServiceImpl<GoodsLadderMapper, GoodsLadder>
    implements GoodsLadderService{

}




