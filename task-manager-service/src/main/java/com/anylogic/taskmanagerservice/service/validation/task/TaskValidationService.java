package com.anylogic.taskmanagerservice.service.validation.task;

import com.anylogic.taskmanagerservice.dto.TaskStatus;

public interface TaskValidationService {

    boolean validateStopExecution(Long taskId, TaskStatus taskStatus);
}
