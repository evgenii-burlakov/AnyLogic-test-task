package com.anylogic.taskmanagerservice.controller;

import com.anylogic.taskmanagerservice.dto.FactorialRequestDto;
import com.anylogic.taskmanagerservice.dto.FactorialResultDto;
import com.anylogic.taskmanagerservice.service.factorial.FactorialTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FactorialTaskController {

    private final FactorialTaskService factorialTaskService;

    @PostMapping("/calculateFactorial")
    public ResponseEntity<FactorialResultDto> calculateFactorial(FactorialRequestDto request) {
        return ResponseEntity.ok(factorialTaskService.calculateFactorial(request.getValue()));
    }
}
