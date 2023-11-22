package com.jasper.consumer;

import com.jasper.pojo.GoodOrder;
import com.jasper.service.GoodOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.jasper.config.RabbitMQConfiguration.DEAD_LETTER_QUEUE_NAME;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeadLetterQueueConsumer {
    private final GoodOrderService orderService;
    @RabbitListener(queues = DEAD_LETTER_QUEUE_NAME)
    public void receive(Message message){
        String  orderId= new String(message.getBody());
        log.info("当前时间：{},死信队列收到订单id:{}", LocalDateTime.now(),orderId);
        GoodOrder order = orderService.getById(orderId);
//        到达时间后订单状态是未付款，则将订单关闭，状态设置为已关闭
        log.info("哈哈哈");
        if(order!=null && order.getStatus()==0){
            log.info("hhh");
            order.setStatus(4);
            orderService.updateById(order);
        }
    }
}
