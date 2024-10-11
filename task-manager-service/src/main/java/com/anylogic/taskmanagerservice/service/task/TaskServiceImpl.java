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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.anylogic.taskmanagerservice.exception.ErrorConstants.TASK_NOT_FOUND;

@Service
@Slf4j
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

        Optional.ofNullable(taskResultMessage).map(TaskResponseMessage::getTaskStatus)
                .ifPresent(
                        s -> taskRepository.updateTaskStatus(savedEntity.getId(), taskResultMessage.getTaskStatus()));

        return Optional.ofNullable(taskResultMessage).orElse(taskMapper.convertToTaskResponseMessage(savedEntity));
    }

    @Override
    public boolean stopTask(Long taskId) {
        log.info("Stop task operation for task with id {} has begun", taskId);

        var taskEntity =
                taskRepository.findById(taskId).orElseThrow(() -> new ApplicationException(TASK_NOT_FOUND, taskId));

        taskValidationService.validateStopExecution(taskEntity.getId(), taskEntity.getStatus());
        var taskResultMessage = messageSenderService.sendMessage(taskMapper.convertToTaskStopRequestMessage(taskId));

        Optional.ofNullable(taskResultMessage).map(TaskResponseMessage::getTaskStatus)
                .ifPresent(s -> taskRepository.updateTaskStatus(taskId, taskResultMessage.getTaskStatus()));

        log.info("Stop task operation has been completed for task with id {} ", taskId);

        return true;
    }
}
