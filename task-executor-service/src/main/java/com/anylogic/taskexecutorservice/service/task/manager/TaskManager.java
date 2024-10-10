package com.anylogic.taskexecutorservice.service.task.manager;

import java.math.BigInteger;
import java.util.function.Function;

public interface TaskManager {

    boolean stopTask(Long taskId);

    BigInteger submitTask(Long taskId, Function<Long, BigInteger> taskFunction);
}
