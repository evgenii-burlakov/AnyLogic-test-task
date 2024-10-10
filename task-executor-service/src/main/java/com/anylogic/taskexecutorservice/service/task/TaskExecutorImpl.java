package com.anylogic.taskexecutorservice.service.task;

import com.anylogic.taskexecutorservice.dto.TaskRequestMessage;
import com.anylogic.taskexecutorservice.dto.TaskResultMessage;
import com.anylogic.taskexecutorservice.dto.TaskType;
import com.anylogic.taskexecutorservice.exception.ApplicationException;
import com.anylogic.taskexecutorservice.mapper.TaskResultMapper;
import com.anylogic.taskexecutorservice.service.task.factorial.FactorialCalculationTask;
import com.anylogic.taskexecutorservice.service.task.manager.TaskManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TaskExecutorImpl implements TaskExecutor {

    private final TaskManager taskManager;
    private final FactorialCalculationTask factorialCalculationTask;
    private final TaskResultMapper taskResultMapper;

    @Override
    public TaskResultMessage executeTask(TaskRequestMessage taskRequestMessage) {
        if (TaskType.FACTORIAL.equals(taskRequestMessage.getTaskType())) {
            try {
                Function<Long, CompletableFuture<BigInteger>> taskFunction =
                        taskId -> factorialCalculationTask.execute(taskId, taskRequestMessage.getValue());
                var result = taskManager.submitTask(taskRequestMessage.getTaskId(), taskFunction);
                return taskResultMapper.convertToSuccessTaskResult(taskRequestMessage.getTaskId(), result);
            } catch (ExecutionException | InterruptedException | ApplicationException e) {
                return taskResultMapper.convertToErrorTaskResult(taskRequestMessage.getTaskId());
            }
        }

        return taskResultMapper.convertToErrorTaskResult(taskRequestMessage.getTaskId());
    }
}
