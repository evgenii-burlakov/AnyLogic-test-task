package com.anylogic.taskmanagerservice.service.validation.factorial;

import com.anylogic.taskmanagerservice.exception.ApplicationException;
import org.springframework.stereotype.Service;

import static com.anylogic.taskmanagerservice.exception.ErrorConstants.FACTORIAL_NOT_APPLICABLE_VALUE;

@Service
public class FactorialValidationServiceImpl implements FactorialValidationService {

    @Override
    public boolean validateCalculateFactorial(Integer value) {
        if (value == null || value < 0) {
            throw new ApplicationException(FACTORIAL_NOT_APPLICABLE_VALUE, value);
        }

        return true;
    }
}
