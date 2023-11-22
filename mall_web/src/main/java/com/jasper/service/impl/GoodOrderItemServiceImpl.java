package com.jasper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.mapper.GoodOrderItemMapper;
import com.jasper.pojo.GoodOrderItem;
import com.jasper.service.GoodOrderItemService;
import org.springframework.stereotype.Service;

/**
* @author 21903
* @description 针对表【good_order_item(订单中所包含的商品)】的数据库操作Service实现
* @createDate 2023-08-08 16:28:30
*/
@Service
public class GoodOrderItemServiceImpl extends ServiceImpl<GoodOrderItemMapper, GoodOrderItem>
    implements GoodOrderItemService{

}




