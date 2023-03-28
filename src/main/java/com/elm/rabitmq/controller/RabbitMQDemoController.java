package com.elm.rabitmq.controller;


import com.elm.rabitmq.dto.MenuOrder;
import com.elm.rabitmq.sender.RabbitMQSender;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/rabbitmq")
public class RabbitMQDemoController {
    private final RabbitMQSender rabbitMQSender;

    public RabbitMQDemoController(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @PostMapping(value = "/sender")
    public String producer(@RequestBody MenuOrder menuOrder) {
        rabbitMQSender.send(menuOrder);
        return "Message sent to the RabbitMQ Queue Successfully";
    }
}