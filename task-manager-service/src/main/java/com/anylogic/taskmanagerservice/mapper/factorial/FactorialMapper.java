package com.anylogic.taskmanagerservice.mapper.factorial;

import com.anylogic.taskmanagerservice.dto.FactorialResultDto;
import com.anylogic.taskmanagerservice.dto.TaskResponseMessage;

public interface FactorialMapper {

    FactorialResultDto convertToFactorialResultDto(TaskResponseMessage taskResponseMessage);
}
