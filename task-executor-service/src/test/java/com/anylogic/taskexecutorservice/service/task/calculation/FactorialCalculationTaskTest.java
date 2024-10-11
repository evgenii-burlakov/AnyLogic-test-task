package com.anylogic.taskexecutorservice.service.task.calculation;

import com.anylogic.taskexecutorservice.exception.ApplicationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.anylogic.taskexecutorservice.util.TestConstants.FACTORIAL_TASK_INCORRECT_VALUE;
import static com.anylogic.taskexecutorservice.util.TestConstants.FACTORIAL_TASK_RESULT;
import static com.anylogic.taskexecutorservice.util.TestConstants.FACTORIAL_TASK_VALUE;
import static com.anylogic.taskexecutorservice.util.TestConstants.TASK_ID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {FactorialCalculationTask.class})
class FactorialCalculationTaskTest {

    @Autowired
    private FactorialCalculationTask factorialCalculationService;

    @Test
    void calculateFactorial() {
        var bigIntegerCompletableFuture = factorialCalculationService.execute(TASK_ID, FACTORIAL_TASK_VALUE);

        assertEquals(bigIntegerCompletableFuture, FACTORIAL_TASK_RESULT);
    }

    @Test
    void calculateFactorialWithIncorrectValueThrowsError() {
        assertThatThrownBy(
                () -> factorialCalculationService.execute(TASK_ID, FACTORIAL_TASK_INCORRECT_VALUE)).isInstanceOf(
                ApplicationException.class);
    }
}