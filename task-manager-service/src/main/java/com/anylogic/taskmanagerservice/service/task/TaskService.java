package com.anylogic.taskmanagerservice.service.task;

import com.anylogic.taskmanagerservice.dto.FactorialResultDto;
import com.anylogic.taskmanagerservice.dto.TaskType;

public interface TaskService {

    FactorialResultDto startTask(Integer value, TaskType taskType);

    boolean stopTask(Long taskId);
}
