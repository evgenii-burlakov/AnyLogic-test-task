package com.anylogic.taskexecutorservice.listener;

import com.anylogic.taskexecutorservice.configuration.MessagingConfig;
import com.anylogic.taskexecutorservice.dto.TaskRequestMessage;
import com.anylogic.taskexecutorservice.service.task.TaskExecutor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageListener {

    private final TaskExecutor taskExecutor;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = MessagingConfig.REQUEST_QUEUE, containerFactory = "rabbitListenerContainerFactory")
    public String receiveMessage(@Payload TaskRequestMessage request) throws JsonProcessingException {
        return objectMapper.writeValueAsString(taskExecutor.executeTask(request));
    }
}
