package com.anylogic.taskmanagerservice.service.message;

import com.anylogic.taskmanagerservice.entity.TaskEntity;

public interface MessageSenderService {

    TaskEntity sendMessage(TaskEntity task);
}
