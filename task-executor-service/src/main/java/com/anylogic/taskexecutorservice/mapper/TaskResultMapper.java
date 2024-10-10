package com.anylogic.taskexecutorservice.mapper;

import com.anylogic.taskexecutorservice.dto.TaskResponseMessage;

import java.math.BigInteger;

public interface TaskResultMapper {

    TaskResponseMessage convertToErrorTaskResult(Long taskId);

    TaskResponseMessage convertToSuccessTaskResult(Long taskId, BigInteger result);
}
