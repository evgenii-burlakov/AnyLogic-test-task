package com.anylogic.taskmanagerservice.mapper.task;

import com.anylogic.taskmanagerservice.dto.TaskRequestMessage;
import com.anylogic.taskmanagerservice.dto.TaskResponseMessage;
import com.anylogic.taskmanagerservice.dto.TaskStatus;
import com.anylogic.taskmanagerservice.dto.TaskType;
import com.anylogic.taskmanagerservice.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    public TaskEntity convertToTaskEntity(Integer value, TaskType taskType, TaskStatus taskStatus) {
        var taskEntity = new TaskEntity();
        taskEntity.setValue(value);
        taskEntity.setType(taskType);
        taskEntity.setStatus(taskStatus);

        return taskEntity;
    }

    @Override
    public TaskRequestMessage convertToTaskRequestMessage(TaskEntity taskEntity) {
        return TaskRequestMessage.builder().taskId(taskEntity.getId()).value(taskEntity.getValue())
                .taskType(taskEntity.getType()).taskStatus(taskEntity.getStatus()).build();
    }

    @Override
    public TaskRequestMessage convertToTaskStopRequestMessage(Long taskId) {
        return TaskRequestMessage.builder().taskId(taskId).taskStatus(TaskStatus.STOPPED).build();
    }

    @Override
    public TaskResponseMessage convertToTaskResponseMessage(TaskEntity taskEntity) {
        return TaskResponseMessage.builder().taskId(taskEntity.getId()).taskStatus(taskEntity.getStatus())
                .build();
    }
}
