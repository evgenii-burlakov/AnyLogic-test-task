package com.anylogic.taskmanagerservice.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

@Configuration
public class MessagingConfig {

    public static final String REQUEST_QUEUE = "taskRequestQueue";
    public static final String RESPONSE_QUEUE = "taskResponseQueue";
    public static final String EXCHANGE = "exchange";
    public static final String REQUEST_BINDING = "task.request";
    public static final String RESPONSE_BINDING = "task.response";

    @Bean
    public Queue requestQueue() {
        return new Queue(REQUEST_QUEUE, true);
    }

    @Bean
    public Queue responseQueue() {
        return new Queue(RESPONSE_QUEUE, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding bindingRequest() {
        return BindingBuilder.bind(requestQueue()).to(exchange()).with(REQUEST_BINDING);
    }

    @Bean
    public Binding bindingResponse() {

        return BindingBuilder.bind(responseQueue()).to(exchange()).with(RESPONSE_BINDING);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        template.setReplyTimeout(50000);
        return template;
    }
}
