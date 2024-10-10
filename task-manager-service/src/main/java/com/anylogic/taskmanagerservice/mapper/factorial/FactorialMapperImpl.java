package com.anylogic.taskmanagerservice.mapper.factorial;

import com.anylogic.taskmanagerservice.dto.FactorialResultDto;
import com.anylogic.taskmanagerservice.dto.TaskResponseMessage;
import org.springframework.stereotype.Component;

@Component
public class FactorialMapperImpl implements FactorialMapper {

    @Override
    public FactorialResultDto convertToFactorialResultDto(TaskResponseMessage taskResponseMessage) {

        return FactorialResultDto.builder().result(taskResponseMessage.getResult()).build();
    }
}
