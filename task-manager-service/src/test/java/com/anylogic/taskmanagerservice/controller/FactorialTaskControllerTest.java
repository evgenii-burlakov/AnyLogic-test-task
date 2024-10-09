package com.anylogic.taskmanagerservice.controller;

import com.anylogic.taskmanagerservice.dto.FactorialResultDto;
import com.anylogic.taskmanagerservice.service.factorial.FactorialTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FactorialTaskController.class)
class FactorialTaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FactorialTaskService factorialTaskService;

    @Test
    void calculateFactorial() throws Exception {
        given(factorialTaskService.calculateFactorial(3)).willReturn(FactorialResultDto.builder().result(BigInteger.valueOf(6)).build());

        mvc.perform(post("/api/calculateFactorial")
                        .param("value", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("result", is(6)));
    }
}