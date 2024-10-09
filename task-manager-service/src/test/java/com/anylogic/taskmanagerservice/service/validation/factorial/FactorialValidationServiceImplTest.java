package com.anylogic.taskmanagerservice.service.validation.factorial;

import com.anylogic.taskmanagerservice.exception.ApplicationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static com.anylogic.taskmanagerservice.util.TestConstants.FACTORIAL_TASK_VALUE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest(classes = {FactorialValidationServiceImpl.class})
class FactorialValidationServiceImplTest {

    @Autowired
    private FactorialValidationServiceImpl factorialValidationService;

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("invalidIntegers")
    void validateCalculateFactorialThrowError(Integer value) {
        assertThatThrownBy(() -> factorialValidationService.validateCalculateFactorial(value)).isInstanceOf(
                ApplicationException.class);

    }

    @Test
    void validateCalculateFactorialNotThrowErrorForPositiveInt() {
        assertThatCode(() -> factorialValidationService.validateCalculateFactorial(FACTORIAL_TASK_VALUE)).doesNotThrowAnyException();
    }

    private static Stream<Arguments> invalidIntegers() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of((Integer) null)
        );
    }
}