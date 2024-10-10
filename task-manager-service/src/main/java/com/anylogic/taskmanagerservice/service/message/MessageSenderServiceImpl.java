package com.anylogic.taskmanagerservice.service.message;

import com.anylogic.taskmanagerservice.configuration.MessagingConfig;
import com.anylogic.taskmanagerservice.dto.TaskRequestMessage;
import com.anylogic.taskmanagerservice.dto.TaskResponseMessage;
import com.anylogic.taskmanagerservice.exception.ApplicationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import static com.anylogic.taskmanagerservice.exception.ErrorConstants.INTERNAL_PROBLEM;

@Service
@RequiredArgsConstructor
public class MessageSenderServiceImpl implements MessageSenderService {

    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public TaskResponseMessage sendMessage(TaskRequestMessage request) {

        try {
            String  taskResultString = (String) amqpTemplate.convertSendAndReceive(MessagingConfig.EXCHANGE, MessagingConfig.REQUEST_BINDING, request);
            return objectMapper.readValue(taskResultString, TaskResponseMessage.class);
        } catch (JsonProcessingException e) {
            throw new ApplicationException(INTERNAL_PROBLEM, request.getTaskId(), e);
        }
    }
}
