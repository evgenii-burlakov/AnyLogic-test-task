package com.anylogic.taskexecutorservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TaskRequestMessage {

    private Long taskId;
    private Integer value;
    private TaskType taskType;
}
