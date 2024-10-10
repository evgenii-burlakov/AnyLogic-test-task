package com.anylogic.taskmanagerservice.service.factorial;

import com.anylogic.taskmanagerservice.dto.FactorialResultDto;
import com.anylogic.taskmanagerservice.dto.TaskStatus;
import com.anylogic.taskmanagerservice.dto.TaskType;
import com.anylogic.taskmanagerservice.exception.ApplicationException;
import com.anylogic.taskmanagerservice.mapper.factorial.FactorialMapper;
import com.anylogic.taskmanagerservice.service.task.TaskService;
import com.anylogic.taskmanagerservice.service.validation.factorial.FactorialValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.anylogic.taskmanagerservice.exception.ErrorConstants.TASK_NOT_EXECUTED;

@Service
@RequiredArgsConstructor
public class FactorialTaskServiceImpl implements FactorialTaskService {

    private final FactorialValidationService factorialValidationService;
    private final FactorialMapper factorialMapper;
    private final TaskService taskService;

    @Override
    public FactorialResultDto calculateFactorial(Integer value) {
        factorialValidationService.validateCalculateFactorial(value);
        var taskResultMessage = taskService.startTask(value, TaskType.FACTORIAL);

        if (taskResultMessage.getTaskStatus() != TaskStatus.FINISHED) {
            throw new ApplicationException(TASK_NOT_EXECUTED, taskResultMessage.getTaskId(), taskResultMessage.getTaskStatus());
        }

        return  factorialMapper.convertToFactorialResultDto(taskResultMessage);
    }
}
