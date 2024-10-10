package com.anylogic.taskexecutorservice.service.task;

import com.anylogic.taskexecutorservice.dto.TaskRequestMessage;
import com.anylogic.taskexecutorservice.dto.TaskResponseMessage;

public interface TaskExecutor {

    TaskResponseMessage executeTask(TaskRequestMessage taskRequestMessage);
}
