package com.anylogic.taskexecutorservice.service.message;

import com.anylogic.taskexecutorservice.configuration.MessagingConfig;
import com.anylogic.taskexecutorservice.dto.TaskRequestMessage;
import com.anylogic.taskexecutorservice.service.task.TaskExecutor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageListenerServiceImpl implements MessageListenerService {

    private final TaskExecutor taskExecutor;
    private final ObjectMapper objectMapper;

    @Override
    @RabbitListener(queues = MessagingConfig.REQUEST_QUEUE)
    public String receiveMessage(@Payload TaskRequestMessage request) throws JsonProcessingException {
        return objectMapper.writeValueAsString(taskExecutor.executeTask(request));
    }
}
