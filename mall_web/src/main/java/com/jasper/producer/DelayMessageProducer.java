package com.jasper.producer;

import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static com.jasper.config.RabbitMQConfiguration.DELAY_EXCHANGE_NAME;
import static com.jasper.config.RabbitMQConfiguration.DELAY_QUEUE_ROUTING_KEY;

@Component
public class DelayMessageProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(String message,String delayTime){
        rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME,DELAY_QUEUE_ROUTING_KEY,message,message1 -> {
        message1.getMessageProperties().setExpiration(delayTime);
        return message1;
        }
        );
    }
}
