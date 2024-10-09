package com.anylogic.taskmanagerservice.mapper;

import com.anylogic.taskmanagerservice.dto.TaskStatus;
import com.anylogic.taskmanagerservice.dto.TaskType;
import com.anylogic.taskmanagerservice.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper{
    public TaskEntity convertToTaskEntity(Integer value, TaskType taskType, TaskStatus taskStatus) {
        var taskEntity = new TaskEntity();
        taskEntity.setValue(value);
        taskEntity.setType(taskType);
        taskEntity.setStatus(taskStatus);

        return taskEntity;
    }
}
