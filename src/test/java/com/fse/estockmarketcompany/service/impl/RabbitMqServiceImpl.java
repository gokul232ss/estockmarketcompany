package com.fse.estockmarketcompany.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Map;

@Primary
@Service
public class RabbitMqServiceImpl {

    public void publishMessage(String fseExchange, String routingKey, Map<String, String> data) {
    }
}
