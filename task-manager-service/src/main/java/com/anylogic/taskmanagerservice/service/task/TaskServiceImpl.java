package com.anylogic.taskmanagerservice.service.task;

import com.anylogic.taskmanagerservice.dto.TaskResponseMessage;
import com.anylogic.taskmanagerservice.dto.TaskStatus;
import com.anylogic.taskmanagerservice.dto.TaskType;
import com.anylogic.taskmanagerservice.exception.ApplicationException;
import com.anylogic.taskmanagerservice.mapper.task.TaskMapper;
import com.anylogic.taskmanagerservice.repository.TaskRepository;
import com.anylogic.taskmanagerservice.service.message.MessageSenderService;
import com.anylogic.taskmanagerservice.service.validation.task.TaskValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.anylogic.taskmanagerservice.exception.ErrorConstants.TASK_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskValidationService taskValidationService;
    private final MessageSenderService messageSenderService;

    @Override
    public TaskResponseMessage startTask(Integer value, TaskType taskType) {
        var taskEntity = taskMapper.convertToTaskEntity(value, taskType, TaskStatus.CREATED);
        var savedEntity = taskRepository.save(taskEntity);
        var taskResultMessage = messageSenderService.sendMessage(taskMapper.convertToTaskRequestMessage(savedEntity));

        savedEntity.setStatus(taskResultMessage.getTaskStatus());

        return taskResultMessage;
    }

    @Override
    public boolean stopTask(Long taskId) {
        var taskEntity =
                taskRepository.findById(taskId).orElseThrow(() -> new ApplicationException(TASK_NOT_FOUND, taskId));

        taskValidationService.validateStopExecution(taskEntity.getId(), taskEntity.getStatus());
        var taskResultMessage = messageSenderService.sendMessage(taskMapper.convertToTaskStopRequestMessage(taskId));

        taskEntity.setStatus(taskResultMessage.getTaskStatus());

        return true;
    }
}
