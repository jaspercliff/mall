package com.jasper.controller;

import com.jasper.common_utils.R;
import com.jasper.dto.OrderDTO;
import com.jasper.pojo.GoodOrder;
import com.jasper.service.GoodOrderService;
import com.jasper.vo.ConfirmOrderVo;
import com.jasper.vo.OrderVo;
import com.jasper.vo.UserOrderVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/webService/order")
@RequiredArgsConstructor
public class OrderController {
    private final GoodOrderService orderService;

    @GetMapping("confirmOrder/{ids}")
    private ConfirmOrderVo confirmOrder(@PathVariable List<Integer> ids){
        return orderService.confirmOrder(ids);
    }

    @PostMapping("addOrder")
    private R<String> addOrder(@RequestBody OrderDTO order){
        return orderService.addOrder(order);
    }

    @GetMapping("getOrderDetail/{orderId}")
    private OrderVo getOrderDetail(@PathVariable String orderId){
            return orderService.getOrderDetail(orderId);
    }

    @GetMapping("getOrderById/{id}")
    private GoodOrder getOrderById(@PathVariable Long id){
        return orderService.getById(id);
    }

    @GetMapping("getAllOrder")
    private List<UserOrderVo> getAllOrder(){
        return orderService.getAllOrder();
    }
    @GetMapping("getOrderByStatus/{status}")
    private List<UserOrderVo> getOrderByStatus(@PathVariable String status){
        return orderService.getOrderByStatus(status);
    }

    @PutMapping("buy")
    private Boolean buy(){
        return orderService.buy();
    }
}
