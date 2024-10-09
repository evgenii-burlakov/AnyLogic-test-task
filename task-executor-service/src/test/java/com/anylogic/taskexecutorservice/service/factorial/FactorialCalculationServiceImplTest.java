package com.anylogic.taskexecutorservice.service.factorial;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.anylogic.taskexecutorservice.util.TestConstants.FACTORIAL_TASK_RESULT;
import static com.anylogic.taskexecutorservice.util.TestConstants.FACTORIAL_TASK_VALUE;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {FactorialCalculationServiceImpl.class})
class FactorialCalculationServiceImplTest {

    @Autowired
    private FactorialCalculationServiceImpl factorialCalculationService;

    @Test
    void calculateFactorial() {
        var bigInteger = factorialCalculationService.calculateFactorial(FACTORIAL_TASK_VALUE);

        assertEquals(bigInteger, FACTORIAL_TASK_RESULT);
    }
}