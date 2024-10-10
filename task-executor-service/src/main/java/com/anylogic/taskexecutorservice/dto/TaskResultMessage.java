package com.anylogic.taskexecutorservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Builder
@Getter
@Setter
public class TaskResultMessage {

    private Long taskId;
    private BigInteger result;
    private TaskStatus taskStatus;
}
