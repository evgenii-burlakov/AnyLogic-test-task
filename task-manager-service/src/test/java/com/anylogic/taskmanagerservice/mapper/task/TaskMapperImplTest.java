package com.anylogic.taskmanagerservice.mapper.task;

import com.anylogic.taskmanagerservice.dto.TaskStatus;
import com.anylogic.taskmanagerservice.dto.TaskType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.anylogic.taskmanagerservice.util.TestConstants.FACTORIAL_TASK_VALUE;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TaskMapperImpl.class})
class TaskMapperImplTest {

    @Autowired
    private TaskMapperImpl taskMapper;

    @Test
    void convertToTaskEntity() {
        var taskEntity = taskMapper.convertToTaskEntity(FACTORIAL_TASK_VALUE, TaskType.FACTORIAL, TaskStatus.CREATED);

        assertNull(taskEntity.getId());
        assertEquals(taskEntity.getValue(), FACTORIAL_TASK_VALUE);
        assertEquals(taskEntity.getType(), TaskType.FACTORIAL);
        assertEquals(taskEntity.getStatus(), TaskStatus.CREATED);
    }
}