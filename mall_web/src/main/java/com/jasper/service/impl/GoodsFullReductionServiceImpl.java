package com.jasper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.mapper.GoodsFullReductionMapper;
import com.jasper.pojo.GoodsFullReduction;
import com.jasper.service.GoodsFullReductionService;
import org.springframework.stereotype.Service;

/**
* @author 21903
* @description 针对表【goods_full_reduction(产品满减表(只针对同商品))】的数据库操作Service实现
* @createDate 2023-08-04 15:13:35
*/
@Service
public class GoodsFullReductionServiceImpl extends ServiceImpl<GoodsFullReductionMapper, GoodsFullReduction>
    implements GoodsFullReductionService{

}




