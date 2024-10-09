package com.anylogic.taskmanagerservice.service.factorial;

import com.anylogic.taskmanagerservice.dto.FactorialResultDto;
import com.anylogic.taskmanagerservice.dto.TaskType;
import com.anylogic.taskmanagerservice.service.task.TaskService;
import com.anylogic.taskmanagerservice.service.validation.factorial.FactorialValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FactorialTaskServiceImpl implements FactorialTaskService {

    private final FactorialValidationService factorialValidationService;
    private final TaskService taskService;

    @Override
    public FactorialResultDto calculateFactorial(Integer value) {
        factorialValidationService.validateCalculateFactorial(value);
        return taskService.startTask(value, TaskType.FACTORIAL);
    }
}
