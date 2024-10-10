package com.anylogic.taskmanagerservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class TaskRequestMessage implements Serializable {

    private Long taskId;
    private Integer value;
    private TaskType taskType;
    private TaskStatus taskStatus;
}
