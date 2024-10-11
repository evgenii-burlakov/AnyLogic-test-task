package com.anylogic.taskmanagerservice.service.factorial;

import com.anylogic.taskmanagerservice.dto.FactorialResultDto;
import com.anylogic.taskmanagerservice.dto.TaskStatus;
import com.anylogic.taskmanagerservice.dto.TaskType;
import com.anylogic.taskmanagerservice.exception.ApplicationException;
import com.anylogic.taskmanagerservice.mapper.factorial.FactorialMapper;
import com.anylogic.taskmanagerservice.service.task.TaskService;
import com.anylogic.taskmanagerservice.service.validation.factorial.FactorialValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.anylogic.taskmanagerservice.exception.ErrorConstants.TASK_NOT_EXECUTED;

@Service
@RequiredArgsConstructor
@Slf4j
public class FactorialTaskServiceImpl implements FactorialTaskService {

    private final FactorialValidationService factorialValidationService;
    private final FactorialMapper factorialMapper;
    private final TaskService taskService;

    @Override
    public FactorialResultDto calculateFactorial(Integer value) {
        log.info("Factorial calculation for value {} has begun", value);

        factorialValidationService.validateCalculateFactorial(value);
        var taskResultMessage = taskService.startTask(value, TaskType.FACTORIAL);

        if (taskResultMessage.getTaskStatus() != TaskStatus.FINISHED) {
            throw new ApplicationException(TASK_NOT_EXECUTED, taskResultMessage.getTaskId(),
                    taskResultMessage.getTaskStatus());
        }

        log.info("Factorial calculation has been completed for value {}, result {}", value,
                taskResultMessage.getResult());

        return factorialMapper.convertToFactorialResultDto(taskResultMessage);
    }
}
