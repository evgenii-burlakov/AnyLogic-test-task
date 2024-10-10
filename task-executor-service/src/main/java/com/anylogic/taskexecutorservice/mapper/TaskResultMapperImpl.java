package com.anylogic.taskexecutorservice.mapper;

import com.anylogic.taskexecutorservice.dto.TaskResultMessage;
import com.anylogic.taskexecutorservice.dto.TaskStatus;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class TaskResultMapperImpl implements TaskResultMapper {


    @Override
    public TaskResultMessage convertToErrorTaskResult(Long taskId) {
        return TaskResultMessage.builder().taskId(taskId).taskStatus(TaskStatus.ERROR).build();
    }

    @Override
    public TaskResultMessage convertToSuccessTaskResult(Long taskId, BigInteger result) {
        return TaskResultMessage.builder().taskId(taskId).result(result).taskStatus(TaskStatus.FINISHED).build();
    }
}
