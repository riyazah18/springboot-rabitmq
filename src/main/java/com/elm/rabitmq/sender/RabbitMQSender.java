package com.elm.rabitmq.sender;

import com.elm.rabitmq.dto.MenuOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Service;
@Service
public class RabbitMQSender {
    private final AmqpTemplate rabbitTemplate;
    private final Queue queue;
    private static Logger logger = LogManager.getLogger(RabbitMQSender.class.toString());

    public RabbitMQSender(AmqpTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void send(MenuOrder menuOrder) {
        rabbitTemplate.convertAndSend(queue.getName(), menuOrder);
        logger.info("Sending Message to the Queue : " + menuOrder.toString());
    }
}