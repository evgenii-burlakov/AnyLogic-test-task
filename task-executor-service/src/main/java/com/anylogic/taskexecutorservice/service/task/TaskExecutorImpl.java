package com.anylogic.taskexecutorservice.service.task;

import com.anylogic.taskexecutorservice.dto.TaskRequestMessage;
import com.anylogic.taskexecutorservice.dto.TaskResponseMessage;
import com.anylogic.taskexecutorservice.dto.TaskStatus;
import com.anylogic.taskexecutorservice.dto.TaskType;
import com.anylogic.taskexecutorservice.exception.ApplicationException;
import com.anylogic.taskexecutorservice.mapper.TaskResultMapper;
import com.anylogic.taskexecutorservice.service.task.calculation.FactorialCalculationTask;
import com.anylogic.taskexecutorservice.service.task.manager.TaskManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TaskExecutorImpl implements TaskExecutor {

    private final TaskManager taskManager;
    private final FactorialCalculationTask factorialCalculationTask;
    private final TaskResultMapper taskResultMapper;

    @Override
    public TaskResponseMessage executeTask(TaskRequestMessage taskRequestMessage) {
        if (TaskStatus.STOPPED.equals(taskRequestMessage.getTaskStatus())) {
            taskManager.stopTask(taskRequestMessage.getTaskId());
            return taskResultMapper.convertToStoppedTaskResult(taskRequestMessage.getTaskId());
        }

        if (TaskType.FACTORIAL.equals(taskRequestMessage.getTaskType())) {
            try {
                Function<Long, BigInteger> taskFunction =
                        taskId -> factorialCalculationTask.execute(taskId, taskRequestMessage.getValue());
                try {
                    BigInteger result = taskManager.submitTask(taskRequestMessage.getTaskId(), taskFunction);
                    return taskResultMapper.convertToSuccessTaskResult(taskRequestMessage.getTaskId(), result);
                } catch (ApplicationException e) {
                    return taskResultMapper.convertToStoppedTaskResult(taskRequestMessage.getTaskId());
                }
            } catch (ApplicationException e) {
                return taskResultMapper.convertToErrorTaskResult(taskRequestMessage.getTaskId());
            }
        }

        return taskResultMapper.convertToErrorTaskResult(taskRequestMessage.getTaskId());
    }
}
