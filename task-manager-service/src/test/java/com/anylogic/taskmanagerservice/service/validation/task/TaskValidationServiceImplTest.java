package com.anylogic.taskmanagerservice.service.validation.task;

import com.anylogic.taskmanagerservice.dto.TaskStatus;
import com.anylogic.taskmanagerservice.exception.ApplicationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static com.anylogic.taskmanagerservice.util.TestConstants.TASK_ID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest(classes = {TaskValidationServiceImpl.class})
class TaskValidationServiceImplTest {

    @Autowired
    private TaskValidationServiceImpl taskValidationService;

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("terminatedStatuses")
    void validateStopExecutionThrowError(TaskStatus taskStatus) {
        assertThatThrownBy(() -> taskValidationService.validateStopExecution(TASK_ID, taskStatus)).isInstanceOf(
                ApplicationException.class);
    }

    @Test
    void validateStopExecutionNotThrowErrorForCreatedState() {
        assertThatCode(() -> taskValidationService.validateStopExecution(TASK_ID,
                TaskStatus.CREATED)).doesNotThrowAnyException();
    }

    private static Stream<Arguments> terminatedStatuses() {
        return Stream.of(
                Arguments.of(TaskStatus.ERROR),
                Arguments.of(TaskStatus.FINISHED),
                Arguments.of(TaskStatus.STOPPED)
        );
    }
}