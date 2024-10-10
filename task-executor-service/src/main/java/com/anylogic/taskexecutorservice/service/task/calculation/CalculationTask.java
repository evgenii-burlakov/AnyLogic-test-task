package com.anylogic.taskexecutorservice.service.task.calculation;

import java.math.BigInteger;

public interface CalculationTask {

    BigInteger execute(Long taskId, Integer value);
}
