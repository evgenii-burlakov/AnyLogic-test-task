package com.anylogic.taskexecutorservice.service.task.factorial;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class FactorialCalculationTask implements CalculationTask {

    @Async
    @Override
    public CompletableFuture<BigInteger> execute(Long taskId, Integer value) {
        BigInteger result = BigInteger.ONE;

        for (int i = 1; i <= value; i++) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Task interrupted, stopping...");
                break;
            }

            try {
                result = result.multiply(BigInteger.valueOf(i));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Task interrupted during sleep");
                break;
            }
        }

        return CompletableFuture.completedFuture(result);
    }
}
