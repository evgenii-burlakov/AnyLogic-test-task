package com.anylogic.taskexecutorservice.service.message;

import com.anylogic.taskexecutorservice.dto.TaskRequestMessage;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessageListenerService {

     String receiveMessage(TaskRequestMessage request) throws JsonProcessingException;
}
