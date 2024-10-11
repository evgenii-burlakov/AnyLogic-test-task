package com.anylogic.taskmanagerservice.service.factorial;


import com.anylogic.taskmanagerservice.dto.FactorialResultDto;
import com.anylogic.taskmanagerservice.dto.TaskResponseMessage;
import com.anylogic.taskmanagerservice.dto.TaskStatus;
import com.anylogic.taskmanagerservice.dto.TaskType;
import com.anylogic.taskmanagerservice.exception.ApplicationException;
import com.anylogic.taskmanagerservice.mapper.factorial.FactorialMapper;
import com.anylogic.taskmanagerservice.service.task.TaskService;
import com.anylogic.taskmanagerservice.service.validation.factorial.FactorialValidationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.anylogic.taskmanagerservice.util.TestConstants.FACTORIAL_INCORRECT_TASK_VALUE;
import static com.anylogic.taskmanagerservice.util.TestConstants.FACTORIAL_TASK_RESULT;
import static com.anylogic.taskmanagerservice.util.TestConstants.FACTORIAL_TASK_VALUE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {FactorialTaskServiceImpl.class})
class FactorialTaskServiceImplTest {

    @Autowired
    private FactorialTaskServiceImpl factorialTaskService;

    @MockBean
    private TaskService taskService;

    @MockBean
    private FactorialValidationService factorialValidationService;

    @MockBean
    private FactorialMapper factorialMapper;

    @Test
    void calculateFactorial() {
        var taskResponseMessage =
                TaskResponseMessage.builder().taskStatus(TaskStatus.FINISHED).result(FACTORIAL_TASK_RESULT).build();
        given(taskService.startTask(FACTORIAL_TASK_VALUE, TaskType.FACTORIAL)).willReturn(
                taskResponseMessage);
        given(factorialMapper.convertToFactorialResultDto(taskResponseMessage)).willReturn(
                FactorialResultDto.builder().result(taskResponseMessage.getResult())
                        .build());

        var factorialResultDto = factorialTaskService.calculateFactorial(FACTORIAL_TASK_VALUE);

        assertEquals(factorialResultDto.getResult(), FACTORIAL_TASK_RESULT);
        verify(taskService, times(1)).startTask(FACTORIAL_TASK_VALUE, TaskType.FACTORIAL);
    }

    @Test
    void calculateFactorialThrowsError() {
        given(factorialValidationService.validateCalculateFactorial(FACTORIAL_INCORRECT_TASK_VALUE)).willThrow(
                ApplicationException.class);

        assertThatThrownBy(() -> factorialTaskService.calculateFactorial(FACTORIAL_INCORRECT_TASK_VALUE)).isInstanceOf(
                ApplicationException.class);
    }
}