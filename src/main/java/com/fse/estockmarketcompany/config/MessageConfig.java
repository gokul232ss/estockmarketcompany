package com.fse.estockmarketcompany.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Value("${com.fse.exchange}")
    private String fseExchange;

    @Value("${com.fse.company.delete.queue}")
    private String deleteQueue;

    @Value("${com.fse.company.routingkey}")
    private String routingKey;

    @Bean
    public Queue createQueue() {
        return new Queue(deleteQueue);
    }

    @Bean
    public TopicExchange createTopicExchange() {
        return new TopicExchange(fseExchange);
    }

    @Bean
    public Binding createBinding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
