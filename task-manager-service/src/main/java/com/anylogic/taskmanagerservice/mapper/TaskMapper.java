package com.anylogic.taskmanagerservice.mapper;

import com.anylogic.taskmanagerservice.dto.TaskStatus;
import com.anylogic.taskmanagerservice.dto.TaskType;
import com.anylogic.taskmanagerservice.entity.TaskEntity;

public interface TaskMapper {

    TaskEntity convertToTaskEntity(Integer value, TaskType taskType, TaskStatus taskStatus);
}
