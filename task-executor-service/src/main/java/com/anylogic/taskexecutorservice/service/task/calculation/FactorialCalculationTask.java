package com.anylogic.taskexecutorservice.service.task.calculation;

import com.anylogic.taskexecutorservice.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

import static com.anylogic.taskexecutorservice.exception.ErrorConstants.FACTORIAL_INCORRECT_VALUE;

@Service
@Slf4j
public class FactorialCalculationTask implements CalculationTask {

    @Override
    public BigInteger execute(Long taskId, Integer value) {
        BigInteger result = BigInteger.ONE;

        if (value < 0) {
            throw new ApplicationException(FACTORIAL_INCORRECT_VALUE, taskId, value);
        }

        for (int i = 1; i <= value; i++) {
            if (Thread.currentThread().isInterrupted()) {
                log.info("Task {} interrupted, stopping...", taskId);
                break;
            }

            try {
                result = result.multiply(BigInteger.valueOf(i));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.info("Task {} interrupted, stopping...", taskId);
                break;
            }
        }

        return result;
    }
}
