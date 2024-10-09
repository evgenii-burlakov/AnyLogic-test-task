package com.anylogic.taskmanagerservice.service.task;

import com.anylogic.taskmanagerservice.dto.FactorialResultDto;
import com.anylogic.taskmanagerservice.dto.TaskStatus;
import com.anylogic.taskmanagerservice.dto.TaskType;
import com.anylogic.taskmanagerservice.exception.ApplicationException;
import com.anylogic.taskmanagerservice.mapper.TaskMapper;
import com.anylogic.taskmanagerservice.repository.TaskRepository;
import com.anylogic.taskmanagerservice.service.message.MessageSenderService;
import com.anylogic.taskmanagerservice.service.validation.task.TaskValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

import static com.anylogic.taskmanagerservice.dto.ErrorConstants.TASK_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskValidationService taskValidationService;
    private final MessageSenderService messageSenderService;

    @Override
    public FactorialResultDto startTask(Integer value, TaskType taskType) {
        var taskEntity = taskMapper.convertToTaskEntity(value, taskType, TaskStatus.CREATED);
        taskRepository.save(taskEntity);
//        messageSenderService.sendMessage();

        return FactorialResultDto.builder().result(BigInteger.valueOf(7)).build();
    }

    @Override
    public boolean stopTask(Long taskId) {
        var taskEntity =
                taskRepository.findById(taskId).orElseThrow(() -> new ApplicationException(TASK_NOT_FOUND, taskId));

        taskValidationService.validateStopExecution(taskEntity.getId(), taskEntity.getStatus());
//        messageSenderService.sendMessage();

        return true;
    }
}
