package com.anylogic.taskexecutorservice.service.task.factorial;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

import static com.anylogic.taskexecutorservice.util.TestConstants.FACTORIAL_TASK_RESULT;
import static com.anylogic.taskexecutorservice.util.TestConstants.FACTORIAL_TASK_VALUE;
import static com.anylogic.taskexecutorservice.util.TestConstants.TASK_ID;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {FactorialCalculationTask.class})
class FactorialCalculationTaskTest {

    @Autowired
    private FactorialCalculationTask factorialCalculationService;

    @Test
    void calculateFactorial() throws ExecutionException, InterruptedException {
        var bigIntegerCompletableFuture = factorialCalculationService.execute(TASK_ID, FACTORIAL_TASK_VALUE);

        assertEquals(bigIntegerCompletableFuture.get(), FACTORIAL_TASK_RESULT);
    }
}