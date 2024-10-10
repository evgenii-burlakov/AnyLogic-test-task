package com.anylogic.taskexecutorservice.mapper;

import com.anylogic.taskexecutorservice.dto.TaskResultMessage;

import java.math.BigInteger;

public interface TaskResultMapper {

    TaskResultMessage convertToErrorTaskResult(Long taskId);

    TaskResultMessage convertToSuccessTaskResult(Long taskId, BigInteger result);
}
