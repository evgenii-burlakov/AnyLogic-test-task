package com.anylogic.taskexecutorservice.service.task.calculation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@Slf4j
public class FactorialCalculationTask implements CalculationTask {

    @Override
    public BigInteger execute(Long taskId, Integer value) {
        BigInteger result = BigInteger.ONE;

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
