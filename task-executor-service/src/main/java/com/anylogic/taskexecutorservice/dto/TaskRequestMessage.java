package com.anylogic.taskexecutorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskRequestMessage implements Serializable {

    private Long taskId;
    private Integer value;
    private TaskType taskType;
    private TaskStatus taskStatus;
}
