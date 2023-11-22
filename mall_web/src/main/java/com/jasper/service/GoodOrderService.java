package com.jasper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jasper.common_utils.R;
import com.jasper.dto.OrderDTO;
import com.jasper.pojo.GoodOrder;
import com.jasper.vo.ConfirmOrderVo;
import com.jasper.vo.OrderVo;
import com.jasper.vo.UserOrderVo;

import java.util.List;

/**
* @author 21903
* @description 针对表【good_order(订单表)】的数据库操作Service
* @createDate 2023-08-08 16:28:30
*/
public interface GoodOrderService extends IService<GoodOrder> {

    ConfirmOrderVo confirmOrder(List<Integer> ids);

     R<String> addOrder(OrderDTO order);

    OrderVo getOrderDetail(String orderId);

    List<UserOrderVo> getAllOrder();

    List<UserOrderVo> getOrderByStatus(String status);

    Boolean buy();
}
