package com.anylogic.taskexecutorservice.service.factorial;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class FactorialCalculationServiceImpl implements FactorialCalculationService {

    @Override
    public BigInteger calculateFactorial(int value) {
        BigInteger result = BigInteger.ONE;

        for (int i = 1; i <= value; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }
}
