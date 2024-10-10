package com.anylogic.taskexecutorservice.service.task.manager;

import com.anylogic.taskexecutorservice.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;

import static com.anylogic.taskexecutorservice.exception.ErrorConstants.TASK_ALREADY_EXIST;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskManagerImpl implements TaskManager {
    private final Map<Long, Future<BigInteger>> tasks = new ConcurrentHashMap<>();

    @Override
    public BigInteger submitTask(Long taskId, Function<Long, CompletableFuture<BigInteger>> taskFunction)
            throws ExecutionException, InterruptedException {

        if (tasks.containsKey(taskId)) {
            throw new ApplicationException(TASK_ALREADY_EXIST, taskId);
        }

        CompletableFuture<BigInteger> future = taskFunction.apply(taskId);

        tasks.put(taskId, future);

        future.thenRun(() -> tasks.remove(taskId));

        log.info("Task {} has been submitted.", taskId);

        return future.get();
    }

    @Override
    public boolean stopTask(Long taskId) {
        Future<?> future = tasks.get(taskId);
        if (future != null) {
            boolean cancelled = future.cancel(true);
            if (cancelled) {
                tasks.remove(taskId);
                log.info("Task {} has been cancelled.", taskId);
            } else {
                log.warn("Task {} could not be cancelled.", taskId);
            }
            return cancelled;
        }
        log.warn("Task {} not found.", taskId);

        return true;
    }
}
