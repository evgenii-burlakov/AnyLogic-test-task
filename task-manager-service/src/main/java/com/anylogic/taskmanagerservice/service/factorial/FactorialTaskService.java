package com.anylogic.taskmanagerservice.service.factorial;

import com.anylogic.taskmanagerservice.dto.FactorialResultDto;

public interface FactorialTaskService {

    FactorialResultDto calculateFactorial(Integer value);
}
