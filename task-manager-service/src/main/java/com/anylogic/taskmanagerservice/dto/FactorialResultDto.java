package com.anylogic.taskmanagerservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Builder
@Getter
@Setter
public class FactorialResultDto {

    private BigInteger result;
}
