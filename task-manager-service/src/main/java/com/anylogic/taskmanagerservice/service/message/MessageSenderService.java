package com.anylogic.taskmanagerservice.service.message;

import com.anylogic.taskmanagerservice.dto.TaskRequestMessage;
import com.anylogic.taskmanagerservice.dto.TaskResponseMessage;

public interface MessageSenderService {

    TaskResponseMessage sendMessage(TaskRequestMessage request);
}
