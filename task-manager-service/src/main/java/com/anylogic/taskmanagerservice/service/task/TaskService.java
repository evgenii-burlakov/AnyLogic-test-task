package com.anylogic.taskmanagerservice.service.task;

import com.anylogic.taskmanagerservice.dto.TaskResponseMessage;
import com.anylogic.taskmanagerservice.dto.TaskType;

public interface TaskService {

    TaskResponseMessage startTask(Integer value, TaskType taskType);

    boolean stopTask(Long taskId);
}
