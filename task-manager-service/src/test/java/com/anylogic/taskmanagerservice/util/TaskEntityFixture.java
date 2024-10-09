package com.anylogic.taskmanagerservice.util;

import com.anylogic.taskmanagerservice.dto.TaskStatus;
import com.anylogic.taskmanagerservice.dto.TaskType;
import com.anylogic.taskmanagerservice.entity.TaskEntity;

import static com.anylogic.taskmanagerservice.util.TestConstants.FACTORIAL_TASK_VALUE;

public class TaskEntityFixture {

    public static TaskEntity buildTaskEntity() {
        var taskEntity = new TaskEntity();
        taskEntity.setValue(FACTORIAL_TASK_VALUE);
        taskEntity.setType(TaskType.FACTORIAL);
        taskEntity.setStatus(TaskStatus.CREATED);

        return taskEntity;
    }
}
