package com.anylogic.taskmanagerservice.service.task;

import com.anylogic.taskmanagerservice.dto.TaskStatus;
import com.anylogic.taskmanagerservice.dto.TaskType;
import com.anylogic.taskmanagerservice.exception.ApplicationException;
import com.anylogic.taskmanagerservice.mapper.task.TaskMapper;
import com.anylogic.taskmanagerservice.repository.TaskRepository;
import com.anylogic.taskmanagerservice.service.message.MessageSenderService;
import com.anylogic.taskmanagerservice.service.validation.task.TaskValidationService;
import com.anylogic.taskmanagerservice.util.TaskEntityFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigInteger;
import java.util.Optional;

import static com.anylogic.taskmanagerservice.util.TestConstants.FACTORIAL_TASK_VALUE;
import static com.anylogic.taskmanagerservice.util.TestConstants.TASK_ID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = {TaskServiceImpl.class})
class TaskServiceImplTest {

    @Autowired
    private TaskServiceImpl taskService;

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private TaskValidationService taskValidationService;

    @MockBean
    private MessageSenderService messageSenderService;

    @Test
    void startTask() {
        var taskEntity = TaskEntityFixture.buildTaskEntity();
        given(taskMapper.convertToTaskEntity(FACTORIAL_TASK_VALUE, TaskType.FACTORIAL, TaskStatus.CREATED)).willReturn(
                taskEntity);
        given(taskRepository.save(taskEntity)).willReturn(taskEntity);


        var factorialResultDto = taskService.startTask(FACTORIAL_TASK_VALUE, TaskType.FACTORIAL);

        assertEquals(factorialResultDto.getResult(), BigInteger.valueOf(7));
    }

    @Test
    void stopTask() {
        var taskEntity = TaskEntityFixture.buildTaskEntity();
        taskEntity.setId(TASK_ID);
        given(taskRepository.findById(TASK_ID)).willReturn(Optional.of(taskEntity));

        var isStopped = taskService.stopTask(TASK_ID);

        assertTrue(isStopped);
    }

    @Test
    void stopNotFoundTask() {
        given(taskRepository.findById(TASK_ID)).willReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.stopTask(TASK_ID)).isInstanceOf(ApplicationException.class);
    }

    @Test
    void stopStoppedTask() {
        var taskEntity = TaskEntityFixture.buildTaskEntity();
        taskEntity.setId(TASK_ID);
        given(taskRepository.findById(TASK_ID)).willReturn(Optional.of(taskEntity));
        given(taskValidationService.validateStopExecution(TASK_ID, taskEntity.getStatus())).willThrow(
                ApplicationException.class);

        assertThatThrownBy(() -> taskService.stopTask(TASK_ID)).isInstanceOf(ApplicationException.class);
    }
}