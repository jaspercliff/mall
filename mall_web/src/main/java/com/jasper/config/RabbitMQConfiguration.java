package com.jasper.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class RabbitMQConfiguration {

//    延迟
    public static final String DELAY_EXCHANGE_NAME="delay.exchange";
    public static final String DELAY_QUEUE_NAME="delay.queue";
    public static final String DELAY_QUEUE_ROUTING_KEY="delay.queue.routing.key";

    public static final String DEAD_LETTER_EXCHANGE_NAME="dead.letter.exchange";
    public static final String DEAD_LETTER_QUEUE_NAME="dead.letter.queue";

    public static final String DEAD_LETTER_QUEUE_ROUTING_KEY="dead.letter.routing.key";

//    delay
    @Bean("delayExchange")
    public DirectExchange delayExchange(){
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }
//    dead letter

    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange(){
        return new DirectExchange(DEAD_LETTER_EXCHANGE_NAME);
    }
//    delay queue a
    @Bean("delayQueue")
    public Queue delayQueueA(){
        HashMap<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange",DEAD_LETTER_EXCHANGE_NAME);
        args.put("x-dead-letter-routing-key",DEAD_LETTER_QUEUE_ROUTING_KEY);
        return QueueBuilder.durable(DELAY_QUEUE_NAME).withArguments(args).build();
    }
    @Bean
    public Binding delayBinding(@Qualifier("delayQueue")Queue queue,
                                 @Qualifier("delayExchange")DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(DELAY_QUEUE_ROUTING_KEY);
    }

    @Bean("deadLetterQueue")
    public Queue deadLetterQueue(){
        return  new Queue(DEAD_LETTER_QUEUE_NAME);
    }
    @Bean
    public Binding deadLetterBinding(@Qualifier("deadLetterQueue")Queue queue,
                                 @Qualifier("deadLetterExchange")DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(DEAD_LETTER_QUEUE_ROUTING_KEY);
    }
}
