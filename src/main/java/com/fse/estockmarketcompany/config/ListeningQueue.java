package com.fse.estockmarketcompany.config;

import com.fse.estockmarketcompany.common.CommonConstant;
import com.fse.estockmarketcompany.service.CompanyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ListeningQueue {

    @Autowired
    private CompanyService service;

    @RabbitListener(queues = CommonConstant.ADD_STOCK_QUEUE)
    public void consumeMessageFromQueue(Map<String, Object> mapData) {
        service.updateCompanyStockDetail(mapData);
    }
}
