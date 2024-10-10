package com.anylogic.taskmanagerservice.mapper.task;

import com.anylogic.taskmanagerservice.dto.TaskRequestMessage;
import com.anylogic.taskmanagerservice.dto.TaskStatus;
import com.anylogic.taskmanagerservice.dto.TaskType;
import com.anylogic.taskmanagerservice.entity.TaskEntity;

public interface TaskMapper {

    TaskEntity convertToTaskEntity(Integer value, TaskType taskType, TaskStatus taskStatus);

    TaskRequestMessage convertToTaskRequestMessage(TaskEntity taskEntity);

    TaskRequestMessage convertToTaskStopRequestMessage(Long taskId);
}
