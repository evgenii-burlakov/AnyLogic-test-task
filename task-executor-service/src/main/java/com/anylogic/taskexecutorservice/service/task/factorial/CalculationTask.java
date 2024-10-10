package com.anylogic.taskexecutorservice.service.task.factorial;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

public interface CalculationTask {

    CompletableFuture<BigInteger> execute(Long taskId, Integer value);
}
