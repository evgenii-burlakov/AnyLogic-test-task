package com.anylogic.taskexecutorservice.mapper;

import com.anylogic.taskexecutorservice.dto.TaskResponseMessage;
import com.anylogic.taskexecutorservice.dto.TaskStatus;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class TaskResultMapperImpl implements TaskResultMapper {


    @Override
    public TaskResponseMessage convertToErrorTaskResult(Long taskId) {
        return TaskResponseMessage.builder().taskId(taskId).taskStatus(TaskStatus.ERROR).build();
    }

    @Override
    public TaskResponseMessage convertToSuccessTaskResult(Long taskId, BigInteger result) {
        return TaskResponseMessage.builder().taskId(taskId).result(result).taskStatus(TaskStatus.FINISHED).build();
    }

    @Override
    public TaskResponseMessage convertToStoppedTaskResult(Long taskId) {
        return TaskResponseMessage.builder().taskId(taskId).taskStatus(TaskStatus.STOPPED).build();
    }
}
