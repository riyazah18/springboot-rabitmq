package com.elm.rabitmq.receiver;

import com.elm.rabitmq.dto.MenuOrder;
import com.rabbitmq.client.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitMQReceiver {
    private static Logger logger = LogManager.getLogger(RabbitMQReceiver.class.toString());
    @Value("${rabbitmq.message.chunk.size}")
    private Integer chunkSize;
    @RabbitListener(queues = "rabbitmq.queue", id = "listener")
    public void receiver(MenuOrder message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG)long tag) throws IOException {
        try {
//            if(13<18) {
//                throw new ArithmeticException("exception occurs");
//            }
            // process the message
            logger.info("MenuOrder listener invoked - Consuming Message with MenuOrder Identifier : " + message.getOrderIdentifier());
            // for chunk count message receiver
            channel.basicQos(chunkSize);
            // acknowledge the message
            channel.basicAck(tag, false);
        } catch (Exception e) {
            // reject and requeue the message if there was an error processing it
            channel.basicNack(tag, true,true);
        }
    }
}