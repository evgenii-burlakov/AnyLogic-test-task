package com.anylogic.taskmanagerservice.service.validation.task;

import com.anylogic.taskmanagerservice.dto.TaskStatus;
import com.anylogic.taskmanagerservice.exception.ApplicationException;
import org.springframework.stereotype.Service;

import static com.anylogic.taskmanagerservice.exception.ErrorConstants.TASK_NOT_EXECUTED;

@Service
public class TaskValidationServiceImpl implements TaskValidationService {

    @Override
    public boolean validateStopExecution(Long taskId, TaskStatus taskStatus) {
        switch(taskStatus) {
            case STOPPED, ERROR, FINISHED -> throw new ApplicationException(TASK_NOT_EXECUTED, taskId, taskStatus);
            default -> {
                return true;
            }
        }
    }
}
