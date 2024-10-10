package com.anylogic.taskexecutorservice.service.task;

import com.anylogic.taskexecutorservice.dto.TaskRequestMessage;
import com.anylogic.taskexecutorservice.dto.TaskResultMessage;

public interface TaskExecutor {

    TaskResultMessage executeTask(TaskRequestMessage taskRequestMessage);
}
