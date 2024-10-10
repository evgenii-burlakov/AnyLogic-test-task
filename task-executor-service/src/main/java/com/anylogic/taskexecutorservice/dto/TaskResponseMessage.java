package com.anylogic.taskexecutorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskResponseMessage implements Serializable {

    private Long taskId;
    private BigInteger result;
    private TaskStatus taskStatus;
}
