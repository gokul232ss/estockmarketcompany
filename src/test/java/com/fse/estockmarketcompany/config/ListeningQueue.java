package com.fse.estockmarketcompany.config;

import com.fse.estockmarketcompany.common.CommonConstant;
import com.fse.estockmarketcompany.model.Stock;
import com.fse.estockmarketcompany.service.CompanyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListeningQueue {
}
