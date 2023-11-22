package com.jasper.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.common_utils.R;
import com.jasper.common_utils.ResultCode;
import com.jasper.config.CustomException;
import com.jasper.dto.OrderDTO;
import com.jasper.mapper.GoodOrderMapper;
import com.jasper.mapper.GoodsMapper;
import com.jasper.pojo.*;
import com.jasper.producer.DelayMessageProducer;
import com.jasper.service.*;
import com.jasper.vo.ConfirmOrderVo;
import com.jasper.vo.OrderVo;
import com.jasper.vo.UserOrderVo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.jasper.config.RabbitMQConfiguration.DELAY_QUEUE_NAME;

/**
* @author 21903
* @description 针对表【good_order(订单表)】的数据库操作Service实现
* @createDate 2023-08-08 16:28:30
*/
@Service
@RequiredArgsConstructor
public class GoodOrderServiceImpl extends ServiceImpl<GoodOrderMapper, GoodOrder>
    implements GoodOrderService{
    private static final Logger logger =LoggerFactory.getLogger(GoodOrderServiceImpl.class);
    private final UserService userService;
    private final CartService cartService;
    private final GoodsService goodsService;
    private final UserReceiveAddressService addressService;
    private final GoodOrderItemService itemService;
    private final GoodsMapper goodsMapper;
    private final DelayMessageProducer delayMessageProducer;
    private final RabbitTemplate rabbitTemplate;
    @Value("${order.delay.time}")
    private Integer delayTime;

    @Override
    public ConfirmOrderVo confirmOrder(List<Integer> ids) {
        if (CollectionUtil.isEmpty(ids)){
            throw new CustomException(ResultCode.VALIDATE_FAILED.getCode(),ResultCode.VALIDATE_FAILED.getMessage());
        }
        List<Cart> carts = cartService.listByIds(ids);
        ConfirmOrderVo confirmOrderVo = new ConfirmOrderVo();
        Integer productTotal = 0;
        BigDecimal priceTotal = new BigDecimal("0");
        BigDecimal freightTotal =new BigDecimal("0");
        BigDecimal payActual=new BigDecimal("0");
//        List<UserReceiveAddress> addressList;
        for (Cart cart : carts) {
            productTotal +=cart.getQuantity();
            priceTotal = priceTotal.add(cart.getPrice().multiply(new BigDecimal(cart.getQuantity())));
            Goods goods = goodsService.getById(cart.getProductId());
            if(goods.getIsPostFree()==0){
                freightTotal = freightTotal.add(new BigDecimal(10.00));
            }
        }
        payActual = priceTotal.add(freightTotal);
        confirmOrderVo.setProductTotal(productTotal);
        confirmOrderVo.setPriceTotal(priceTotal);
        confirmOrderVo.setFreightTotal(freightTotal);
        confirmOrderVo.setPayActual(payActual);
//查询用户
//        获取用户积分
        User user = getUser();
        confirmOrderVo.setIntegration(user.getIntegration());
//        获取用户的收货地址
        LambdaQueryWrapper<UserReceiveAddress> addressWrapper = new LambdaQueryWrapper<>();
        addressWrapper.eq(UserReceiveAddress::getMemberId,user.getId());
        List<UserReceiveAddress> addresseslist = addressService.list(addressWrapper);
        confirmOrderVo.setAddressList(addresseslist);
        return confirmOrderVo;
    }

    @Override
    @Transactional
    public R<String> addOrder(OrderDTO orderDTO) {
//        user
        User user = getUser();
        orderDTO.setMemberId(user.getId());
        orderDTO.setMemberUsername(user.getUsername());
        Integer check = orderDTO.getCheck();
        orderDTO.setPayType(1);
        orderDTO.setStatus(0);
        orderDTO.setOrderType(0);
        orderDTO.setAutoConfirmDay(10);
        UserReceiveAddress address = addressService.getById(check);
//        address
        orderDTO.setReceiverName(address.getName());
        orderDTO.setReceiverPhone(address.getPhoneNumber());
        orderDTO.setReceiverPostCode(address.getPostCode());
        orderDTO.setReceiverProvince(address.getProvince());
        orderDTO.setReceiverCity(address.getCity());
        orderDTO.setReceiverRegion(address.getRegion());
        orderDTO.setReceiverDetailAddress(address.getDetailAddress());
        GoodOrder goodOrder = BeanUtil.copyProperties(orderDTO, GoodOrder.class);
//order items
        ArrayList<Long> orderIds = orderDTO.getOrderIds();
        ArrayList<Long> productIds = new ArrayList<>();
        orderIds.forEach(
                orderId ->{
                    LambdaQueryWrapper<Cart> aeq = new LambdaQueryWrapper<Cart>().eq(Cart::getId, orderId);
                    Cart cart = cartService.getOne(aeq);
//                    判断库存
                    int rows = goodsMapper.updateStock(cart.getProductId(),cart.getQuantity());
                    if (rows<=0) {
                        throw new CustomException(2007,"库存不足");
                    }
                    productIds.add(cart.getProductId());
                }
        );
//        保存实际的订单
       super.save(goodOrder);
        productIds.forEach(
                productId ->{
//              保存order对应的商品
                    GoodOrderItem goodOrderItem = new GoodOrderItem();
                    goodOrderItem.setOrderId(goodOrder.getId());
                    goodOrderItem.setProductId(productId);
//                    查询商品
                    Goods goods = goodsService.getById(productId);
                    Optional.ofNullable(goods).ifPresent(goods1 -> {
                        goodOrderItem.setProductPic(goods1.getPicture());
                        goodOrderItem.setProductName(goods1.getName());
                        goodOrderItem.setProductPrice(goods1.getPrice());
                    });
                    itemService.save(goodOrderItem);
                }
        );
//        加入订单id到延迟队列中
        delayMessageProducer.send(goodOrder.getId().toString(),delayTime.toString());
        return R.success(goodOrder.getId().toString());
    }

    @Override
    public OrderVo getOrderDetail(String orderId) {
        OrderVo orderVo = new OrderVo();
        GoodOrder order = super.getById(orderId);
        if(order!=null) {
            orderVo.setPayAmount(order.getPayAmount());
            orderVo.setAddress(
                    order.getReceiverName() + "-" +
                            order.getReceiverPhone() + "-" +
                            order.getReceiverProvince() + "-" +
                            order.getReceiverCity() + "-" +
                            order.getReceiverRegion() + "-" +
                            order.getReceiverDetailAddress());
            orderVo.setDelayTime(delayTime);
            return orderVo;
        }
        return null;
    }

    @Override
    public List<UserOrderVo> getAllOrder() {
        List<GoodOrder> orderList = super.list();
        ArrayList<UserOrderVo> userOrderVos = new ArrayList<>();
        orderList.forEach(
                order -> {
                    UserOrderVo userOrderVo = new UserOrderVo();
                    userOrderVo.setStatus(order.getStatus());
                    userOrderVo.setId(order.getId());
                    userOrderVo.setPayAmount(order.getPayAmount());
                    userOrderVo.setCreateTime(order.getCreateTime());
                    ArrayList<GoodOrderItem> items = new ArrayList<>();
                    LambdaQueryWrapper<GoodOrderItem> wrapper = new LambdaQueryWrapper<>();
                    wrapper.eq(GoodOrderItem::getOrderId,order.getId());
                    List<GoodOrderItem> itemList = itemService.list(wrapper);
                    items.addAll(itemList);
                    userOrderVo.setItems(items);
                    userOrderVos.add(userOrderVo);
                }
        );
        return userOrderVos;
    }

    @Override
    public List<UserOrderVo> getOrderByStatus(String status) {
        List<UserOrderVo> allOrder = getAllOrder();
        return allOrder.stream().filter(item -> {
                    return item.getStatus().toString().equals(status);
        }
        ).collect(Collectors.toList());
    }

    /**
     * 支付
     * @param id
     */
    @Override
    public Boolean buy() {
//        从消息队列中取出订单id
        Object o = rabbitTemplate.receiveAndConvert(DELAY_QUEUE_NAME);
        Optional.ofNullable(o).ifPresent(
                o1 -> {
                    GoodOrder order = super.getById(o1.toString());
                    order.setStatus(1);
                    super.updateById(order);
                }
        );
        return null;
    }


    public User getUser(){
        String email = " ";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            email = authentication.getName();
        }else{
            throw new CustomException(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage());
        }
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(User::getEmail,email);
        User user = userService.getOne(userWrapper);
        return user;
    }
}




