package com.fse.estockmarketcompany.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class RabbitMqServiceImpl {

    @Autowired
    private RabbitTemplate template;

    public void publishMessage(String fseExchange, String routingKey, Map<String, String> data) {
        template.convertAndSend(fseExchange, routingKey, data);
    }
}
