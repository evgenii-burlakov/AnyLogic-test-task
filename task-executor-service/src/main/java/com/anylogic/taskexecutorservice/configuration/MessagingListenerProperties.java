package com.anylogic.taskexecutorservice.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("task-executor-service.listener")
public class MessagingListenerProperties {

    private Integer concurrentConsumers;
    private Integer maxConcurrentConsumers;
}